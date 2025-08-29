class Seniors{
    private int[] ages = new int[2];
    private int num;

    public Seniors() {
        num = 2;
        ages[0] = 30;
        ages[1] = 40;
    
    }
    public int getNum(){
        return num;
    }
    
    public int[] getAges(){
        return ages;
    }

}


public class SeniorsTest{
    public void main(String[] args){
        Seniors seniors = new Seniors();
        int num = seniors.getNum();
        System.out.println(num);
        num = -100;
        num = seniors.getNum();
        System.out.println(num);

    }
}