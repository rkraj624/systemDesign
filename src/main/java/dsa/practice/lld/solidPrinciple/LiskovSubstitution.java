package dsa.practice.lld.solidPrinciple;

/**
* Here we are solving the LSP with correct implementation
* */

public class LiskovSubstitution {
    public static void main(String[] args) {
        PrintableDocument document = new PdfsDocument();
        document.open();
        document.print();
        document.save();
    }
}

class Documents {
    public void save() {
        System.out.println(this.getClass().getSimpleName() + " saved.");
    }

    public void open() {
        System.out.println("Opening " + this.getClass().getSimpleName());
    }
}

class PrintableDocument extends Documents{
    public void print(){
        System.out.println("Printing from " + this.getClass().getSimpleName());
    }
}

class PdfsDocument extends PrintableDocument{

}

class WrdDocument extends PrintableDocument{

}

class TxtDocument extends Documents{

}