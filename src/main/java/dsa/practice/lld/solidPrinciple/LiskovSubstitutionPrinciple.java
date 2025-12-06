package dsa.practice.lld.solidPrinciple;


/**
 * <b>Violation:</b> LSP is broken because <code>TextDocument</code> throws an error when calling <code>print()</code>. <br>
 * Parent class allows the action, but the child changes behavior unexpectedly. <br><br>
 * <i>See:</i> {@link LiskovSubstitution}
 */


public class LiskovSubstitutionPrinciple {
    public static void main(String[] args) {
        Document document = new Document();
        document.open();
        document.print();
        document.save();
    }
}

class Document{
    public void print() {
        System.out.println("Docs from " + this.getClass().getSimpleName());
    }

    public void save() {
        System.out.println(this.getClass().getSimpleName() + " saved.");
    }

    public void open() {
        System.out.println("Opening " + this.getClass().getSimpleName());
    }
}

class TextDocument extends Document{

    @Override
    public void print() {
        throw new UnsupportedOperationException("getting error while reading " + this.getClass().getSimpleName());
    }

    @Override
    public void save() {
        System.out.println(this.getClass().getSimpleName() + " saved.");
    }

    @Override
    public void open() {
        System.out.println("Opening " + this.getClass().getSimpleName());
    }
}

class PDfDocument extends Document{

    @Override
    public void print() {
        System.out.println("Docs from " + this.getClass().getSimpleName());
    }

    @Override
    public void save() {
        System.out.println(this.getClass().getSimpleName() + " saved.");
    }

    @Override
    public void open() {
        System.out.println("Opening " + this.getClass().getSimpleName());
    }
}

class WordDocument extends Document{

    @Override
    public void print() {
        System.out.println("Docs from " + this.getClass().getSimpleName());
    }

    @Override
    public void save() {
        System.out.println(this.getClass().getSimpleName() + " saved.");
    }

    @Override
    public void open() {
        System.out.println("Opening " + this.getClass().getSimpleName());
    }
}
