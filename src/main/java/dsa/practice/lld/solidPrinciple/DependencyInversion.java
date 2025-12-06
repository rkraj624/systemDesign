package dsa.practice.lld.solidPrinciple;
/**
* High-level modules should not depend on low-level modules.
* Both should depend on abstractions.
* Abstractions should not depend on details.
* Details should depend on abstractions.
 * <br></br>
* 🎯 <b>Goal</b>
✔ Reduce tight coupling
✔ Make code easier to unit test
✔ Allow plugging new implementations without changing business logic
* */


public class DependencyInversion {
    public static void main(String[] args) {
        UserService userService = new UserService(new MongoRepository());
        userService.findById();
    }
}

class UserService{
    private final Repository repository;

    public UserService(Repository repository) {
        this.repository = repository;
    }

    public void findById(){
        repository.findById();
        System.out.println("User found in "+ repository.getClass().getSimpleName() + " DB.");
    }
}

interface Repository{
    void findById();
    void findByName();
}

class SQLRepository implements Repository{

    @Override
    public void findById() {

    }

    @Override
    public void findByName() {

    }
}

class MongoRepository implements Repository{

    @Override
    public void findById() {

    }

    @Override
    public void findByName() {

    }
}
