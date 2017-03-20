package hashmap;

public class Main {
    public static void main(String[] args){
        HashMap<Integer,String> map = new HashMap(4);
        
        map.append(3, "Three");
        map.append(6, "Six"  );
        map.append(7, "Seven");
        map.append(1, "One"  );
        map.append(8, "Eight");
        
        System.out.println(map);
        
        System.out.println(map.search(10));
        
        map.delete(7);
        System.out.println("");
        
        System.out.println(map);
        
//        LinkedList<Integer,String> list = new LinkedList<>();
//        list.append(2, "two");
//        list.append(3, "three");
//        list.append(5, "five");
//        list.append(6, "six");
//        list.append(7, "seven");
//        
//        System.out.println(list.print());
    }
}
