public class Test {
    public static void main(String[] args) {
        String s = "{\n" +
                "   \"result\":{\n" +
                "      \"addressbook_id\":21620\n" +
                "   }\n" +
                "} ";
        String[] strings = s.split("\n");
        for (String string : strings) {
            System.out.println(string + " !!! ");
        }

        String[] res = strings[2].split(":");
        System.out.println(new Integer(res[1]));
    }
}
