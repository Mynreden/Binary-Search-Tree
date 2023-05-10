public class Main {
    public static void main(String[] args) {
        BST<Integer, String> bst = new BST<Integer, String>();
        bst.put(15, "sultan");
        bst.put(13, "a");
        bst.put(8, "ksd");
        bst.put(17, "dfjl");
        bst.put(16, "ds");
        bst.put(20, "kjl");
        bst.put(14, "sdf");
        bst.put(18, "sdsf");

        System.out.println("In order iterating");
        for (var elem : bst) {
            System.out.println(elem + ", ");
        }
        System.out.println("\nDeleting element by key 18");
        bst.delete(18);
        System.out.println("Result:");
        for (var elem : bst) {
            System.out.println(elem + ", ");
        }
        System.out.println("Size equal: " + bst.size());

        System.out.println("\nPut element by key 19 and value 'fgdg'");
        bst.put(19, "fgdg");
        for (var elem : bst) {
            System.out.println(elem + ", ");
        }
        System.out.println("Size equal: " + bst.size());

        System.out.println("\nGet value of element with key 15: " + bst.get(15));
    }
}
