/**
 * This GuitarSimulator object . . .
 * 
 * @author  
 * @version 
 */
public class GuitarSimulator
{
    String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    GuitarString[] strings = new GuitarString[keyboard.length()];
    public GuitarSimulator(){
        for(int i = 0; i < strings.length; i++){
            double frequency = 440*Math.pow(1.05956,(i - 24));
            strings[i] = new GuitarString(frequency);
        }
    }
    public static void main(String[] args) {
        GuitarSimulator simulator = new GuitarSimulator();
        while(true){
            if(StdDraw.hasNextKeyTyped()){
                char key = StdDraw.nextKeyTyped();
                int index = simulator.keyboard.indexOf(key);
                if(index != -1){
                    simulator.strings[index].pluck();
                }
            }

            double sample = 0.0;
            for (GuitarString string : simulator.strings) {
                sample += string.sample();
            }
            StdAudio.play(sample);

            for (GuitarString string : simulator.strings) {
                string.tic();
            }
        }
    }
}
