package org.hamcrest.generator;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;

public final class HamcrestFactoryWriterTest {
    private final StringWriter output = new StringWriter();
    private final FactoryWriter factoryWriter = new HamcrestFactoryWriter(null, null, output);

    @Test public void
    writesMethodDelegationMethod() throws IOException {
        FactoryMethod method = new FactoryMethod("com.example.MyMatcher", "anyObject", "matcher.ReturnType");

        factoryWriter.writeMethod(method.getName(), method);
        assertEquals("" +
                "\tpublic static matcher.ReturnType anyObject() {\n" +
                "\t\treturn com.example.MyMatcher.anyObject();\n" +
                "\t}\n" +
                "\n",
                output.toString());
    }

    @Test public void
    writesCompleteJavaSource() throws IOException {
        final FactoryWriter writer = new HamcrestFactoryWriter("com.some.package", "SomeClass", output);
        writer.writeHeader();
        writer.writeMethod("method1", new FactoryMethod("com.example.MyMatcher", "method1", "matcher.ReturnType"));
        writer.writeMethod("method2", new FactoryMethod("com.example.MyMatcher", "method2", "matcher.ReturnType"));
        writer.writeFooter();
        writer.close();

        assertEquals("" +
                "// Generated source.\n" +
                "package com.some.package;\n" +
                "\n" +
                "public class SomeClass {\n" +
                "\n" +
                "\tpublic static matcher.ReturnType method1() {\n" +
                "\t\treturn com.example.MyMatcher.method1();\n" +
                "\t}\n" +
                "\n" +
                "\tpublic static matcher.ReturnType method2() {\n" +
                "\t\treturn com.example.MyMatcher.method2();\n" +
                "\t}\n" +
                "\n" +
                "}\n",
                output.toString());
    }

    @Test public void
    writesGenerifiedMatcherType() throws IOException {
        FactoryMethod method = new FactoryMethod("com.example.MyMatcher", "anyString", "matcher.ReturnType");
        method.setGenerifiedType("String");

        factoryWriter.writeMethod(method.getName(), method);
        assertEquals("" +
                "\tpublic static matcher.ReturnType<String> anyString() {\n" +
                "\t\treturn com.example.MyMatcher.anyString();\n" +
                "\t}\n" +
                "\n",
                output.toString());
    }

    @Test public void
    writesAdvancedGenerifiedMatcherType() throws IOException {
        FactoryMethod method = new FactoryMethod("com.example.MyMatcher", "weirdThing", "matcher.ReturnType");
        method.setGenerifiedType("java.util.Map<com.Foo<Cheese>,?>");

        factoryWriter.writeMethod(method.getName(), method);
        assertEquals("" +
                "\tpublic static matcher.ReturnType<java.util.Map<com.Foo<Cheese>,?>> weirdThing() {\n" +
                "\t\treturn com.example.MyMatcher.weirdThing();\n" +
                "\t}\n" +
                "\n",
                output.toString());
    }

    @Test public void
    writesParameters() throws IOException {
        FactoryMethod method = new FactoryMethod("com.example.MyMatcher", "between", "matcher.ReturnType");
        method.addParameter("int[]", "lower");
        method.addParameter("com.blah.Cheesable<Long>...", "upper");

        factoryWriter.writeMethod(method.getName(), method);
        assertEquals("" +
                "\tpublic static matcher.ReturnType between(int[] lower, com.blah.Cheesable<Long>... upper) {\n" +
                "\t\treturn com.example.MyMatcher.between(lower, upper);\n" +
                "\t}\n" +
                "\n",
                output.toString());
    }

    @Test public void
    writesExceptions() throws IOException {
        FactoryMethod method = new FactoryMethod("com.example.MyMatcher", "tricky", "matcher.ReturnType");
        method.addException("java.io.IOException");
        method.addException("com.foo.CheeselessException");

        factoryWriter.writeMethod(method.getName(), method);
        assertEquals("" +
                "\tpublic static matcher.ReturnType tricky() throws java.io.IOException, com.foo.CheeselessException {\n" +
                "\t\treturn com.example.MyMatcher.tricky();\n" +
                "\t}\n" +
                "\n",
                output.toString());
    }

    @Test public void
    writesGenericTypeParameters() throws IOException {
        FactoryMethod method = new FactoryMethod("com.example.MyMatcher", "tricky", "matcher.ReturnType");
        method.addGenericTypeParameter("T");
        method.addGenericTypeParameter("V extends String & Cheese");
        method.addParameter("T", "t");
        method.addParameter("List<V>", "v");

        factoryWriter.writeMethod(method.getName(), method);
        assertEquals("" +
                "\tpublic static <T, V extends String & Cheese> matcher.ReturnType tricky(T t, List<V> v) {\n" +
                "\t\treturn com.example.MyMatcher.<T,V>tricky(t, v);\n" +
                "\t}\n" +
                "\n",
                output.toString());
    }

    @Test public void
    writesJavaDoc() throws IOException {
        FactoryMethod method = new FactoryMethod("com.example.MyMatcher", "needsDoc", "matcher.ReturnType");
        method.setJavaDoc("This is a complicated method.\nIt needs docs.\n\n@see MoreStuff");

        factoryWriter.writeMethod(method.getName(), method);
        assertEquals("" +
                "\t/**\n" +
                "\t * This is a complicated method.\n" +
                "\t * It needs docs.\n" +
                "\t * \n" +
                "\t * @see MoreStuff\n" +
                "\t */\n" +
                "\tpublic static matcher.ReturnType needsDoc() {\n" +
                "\t\treturn com.example.MyMatcher.needsDoc();\n" +
                "\t}\n" +
                "\n",
                output.toString());
    }

    @Test public void
    writesMethodWithNameOverridden() throws IOException {
        FactoryMethod method = new FactoryMethod("com.example.MyMatcher", "eq", "matcher.ReturnType");

        factoryWriter.writeMethod("anotherName", method);
        assertEquals("" +
                "\tpublic static matcher.ReturnType anotherName() {\n" +
                "\t\treturn com.example.MyMatcher.eq();\n" +
                "\t}\n" +
                "\n",
                output.toString());
    }
}
