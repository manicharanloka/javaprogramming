import java.util.HashMap;
import java.util.Map;

//An immutable class
final class Student {

    // private and final member attributes
    private final String name;
    private final int regNo;
    private final Map<String, String> metadata;

    //Constructor of immutable class
    public Student(String name, int regNo, Map<String, String> metadata) {
        this.name = name;
        this.regNo = regNo;
        Map<String, String> tempMap = new HashMap<>();
        for (String key : metadata.keySet()) {
            tempMap.put(key, metadata.get(key));
        }
        this.metadata = tempMap;
    }

    // Note that there should not be any setters
    public String getName() {
        return name;
    }

    public int getRegNo() {
        return regNo;
    }

    public Map<String, String> getMetadata() {
        Map<String, String> tempMap = new HashMap<>();
        for (String key : this.metadata.keySet()) {
            tempMap.put(key, this.metadata.get(key));
        }
        return tempMap; // returns a deep copy of reference object
    }

    @Override
    public String toString() {
        return "Name: " + name + ", RegNo: " + regNo + ", Metadata: " + metadata;
    }
}

public class ImmutableClass {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("1", "first");
        map.put("2", "second");
        Student student = new Student("ABC", 123, map);
        System.out.println(student);

//        Uncommenting below line causes error
//        student.regNo=102;
        map.put("3", "third");
        System.out.println(student);
        student.getMetadata().put("4", "fourth");
        System.out.println(student);
    }
}