package cc.retzlaff.timon;

public class Stringy {
    String string;

    public Stringy(String string) {
        if (string != null) {
            this.string = string;
        } else {
            throw new IllegalStateException("The string you want to insert in " + this + " is null.");
        }
    }

    public void print() {
        System.out.println(this.string);
    }

    public static void print(String string) {
        System.out.println(string);
    }

    public static void print(String[] strings) {
        String[] var1 = strings;
        int var2 = strings.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            String s = var1[var3];
            print(s);
        }

    }
}
