public class Time1{
    private int hour;
    private int minute;
    private int second;
    

    public void setTime(int hour, int minute, int second){
        if(hour<0 || hour>=24 || minute < 0 ||  minute >= 60 || second < 0 || second >=60 ){
    
        throw new IllegalArgumentException("hour, minute and/or second was out of range");
        }
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
    public String toUniverseString(){
        return String.format("%02d:%02d:%02d", hour, minute, second);   
    }
}