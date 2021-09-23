package a8se2020ws;

import java.lang.reflect.*;

import a8se2020ws.assignment8_int.Reconstructor;

public class A8SE2020WS implements Reconstructor {

    // Variables used to produces right string/ format
    private String skeleton;
    private String indent = "    ";
    private String br = "\n";
    private int paramCount = 0;

    /** reconstrucst a .java file from a .class file */
    @Override
    public String reconstructFromClass(Class c) {

        if (c == null) {
            return null;
        }

        skeleton = "";

        makeBase(c);
        makeFields(c);
        makeConst(c);
        makeMeth(c);

        System.out.println(skeleton);
        return skeleton;
    }

    /** Basically same as above, just without c.getName... etc */
    @Override
    public String reconstructFromClassName(String fullClassname) throws ClassNotFoundException {
        if (fullClassname == null) {
            return null;
        }

        return reconstructFromClass(Class.forName(fullClassname));
    }
    

    private void makeBase(Class c) {
        // writes class declaration in required order
        if (c.getPackageName() != null) 
            skeleton += "package " + c.getPackageName() + ';' + br + br;

        String classDekl = c.toGenericString().replace(c.getPackageName() + ".", "");

        // Standard superclass is Object hence the check which prevents an unnecessary
        // extend statement
        if (!c.getSuperclass().getSimpleName().equals("Object")) {
            skeleton += classDekl + " extends " + c.getSuperclass().getSimpleName() + ' ';
        } else {
            skeleton += classDekl + ' ';
        }

        // makes implemented interfaces
        Class[] interfaces = c.getInterfaces();

        if (interfaces.length > 0) {
            int i = 0;
            skeleton += " implements ";
            if (interfaces.length > 1) {
                for (i = 0; i < interfaces.length - 1; i++) {
                    skeleton += interfaces[i].getSimpleName() + ", ";
                }
            }
            skeleton += interfaces[i].getSimpleName() + '{' + br + indent;
        } else
            skeleton += '{' + br + indent;
    }

    private void makeConst(Class c) {
        Constructor[] construc = c.getDeclaredConstructors();

        for (int i = 0; i < construc.length; i++) {
            skeleton += c.getSimpleName() + "(";
            Class[] paramType = construc[i].getParameterTypes();

            if (paramType.length > 0) {
                int j = 0;
                if (paramType.length > 1) {
                    for (j = 0; j < paramType.length - 1; j++) {
                        skeleton += paramType[j].getSimpleName() + " arg" + paramCount++ + ", ";
                    }
                }
                skeleton += paramType[j].getSimpleName() + " arg" + paramCount++;
            }
            skeleton += "){" + br + indent + "}" + br + br;
        }
    }

    private void makeFields(Class c) {
        Field[] fields = c.getDeclaredFields();

        String langHelper;

        for (int i = 0; i < fields.length; i++) {
            String fieldStr = fields[i].toString();

            langHelper = fields[i].getType().toString().replace("class ", "");
            fieldStr = fieldStr.replace(c.getCanonicalName() + '.', "");
            fieldStr = fieldStr.replace(langHelper, fields[i].getType().getSimpleName());
            skeleton += fieldStr + ';' + br + indent;
        }

        skeleton += br + indent;
    }

    private void makeMeth(Class c) {
        Method[] meths = c.getDeclaredMethods();

        for (int i = 0; i < meths.length; i++) {

            String methStr = meths[i].toString() + '{';
            String cut = methStr.substring(methStr.indexOf('(') + 1, methStr.indexOf(')'));
            methStr = methStr.replace(c.getCanonicalName() + '.', "");
            methStr = methStr.replace(cut, "");

            Class[] paramType = meths[i].getParameterTypes();

            if (paramType.length > 0) {
                int j = 0;
                if (paramType.length > 1) {
                    for (j = 0; j < paramType.length - 1; j++)
                        methStr = methStr.replace(")", paramType[j].getSimpleName() + " arg" + paramCount++ + ", )");
                }
                methStr = methStr.replace(")", paramType[j].getSimpleName() + " arg" + paramCount++ + ")");
                skeleton += indent + methStr + br + indent + indent;
            }

            if (meths[i].getReturnType().isPrimitive()) {
                if (meths[i].getReturnType().getSimpleName().equals("boolean"))
                    skeleton += "return true;";
                else
                    skeleton += "return 0;";
            } 
            else
                skeleton += "return null;";

            skeleton += br + indent + '}';
        }

        skeleton += br + "}"; // end of .class
    }
}
