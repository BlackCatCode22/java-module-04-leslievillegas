public class Animal {
    private String name;
    private int age;
    private String species;

    public Animal(String name, int age, String species) {
        this.name = name;
        this.age = age;
        this.species = species;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getSpecies() { return species; }
}

class Hyena extends Animal {
    public Hyena(String name, int age) { super(name, age, "Hyena"); }
}

class Lion extends Animal {
    public Lion(String name, int age) { super(name, age, "Lion"); }
}

class Tiger extends Animal {
    public Tiger(String name, int age) { super(name, age, "Tiger"); }
}

class Bear extends Animal {
    public Bear(String name, int age) { super(name, age, "Bear"); }
}
