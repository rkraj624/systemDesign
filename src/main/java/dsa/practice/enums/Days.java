package dsa.practice.enums;

public enum Days implements EnumInterface{


    MONDAY{
        @Override
        public void toLoweCase() {
            System.out.println("this is the enum method for "+ this.name().toLowerCase());
        }

        @Override
        public void print() {
            System.out.println("this is the enum method for Monday");
        }
        @Override
        public void days() {
            System.out.println("1st day of week");
        }
    },
    TUESDAY {
        @Override
        public void toLoweCase() {
            System.out.println("this is the enum method for "+ this.name().toLowerCase());
        }

        @Override
        public void days() {
            System.out.println("2nd day of week");
        }
    },
    WEDNESDAY {
        @Override
        public void toLoweCase() {
            System.out.println("this is the enum method for "+ this.name().toLowerCase());
        }

        @Override
        public void days() {
            System.out.println("3rd day of week");
        }
    },
    THURSDAY {
        @Override
        public void toLoweCase() {
            System.out.println("this is the enum method for "+ this.name().toLowerCase());
        }

        @Override
        public void days() {
            System.out.println("4th day of week");
        }
    },
    FRIDAY {
        @Override
        public void toLoweCase() {
            System.out.println("this is the enum method for "+ this.name().toLowerCase());
        }

        @Override
        public void days() {
            System.out.println("5th day of week");
        }
    },
    SATURDAY {
        @Override
        public void toLoweCase() {
            System.out.println("this is the enum method for "+ this.name().toLowerCase());
        }

        @Override
        public void days() {
            System.out.println("6th day of week");
        }
    },
    SUNDAY {
        @Override
        public void toLoweCase() {
            System.out.println("this is the enum method for "+ this.name().toLowerCase());
        }

        @Override
        public void days() {
            System.out.println("1st day of week");
        }
    };
    public void print(){
        System.out.println("this is the enum method");
    }

    public abstract void days();
}
