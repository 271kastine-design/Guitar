/**
 * This GuitarString object . . .
 * 
 * @author  
 * @version 
 */
public class GuitarString 
{
    private double frequency;
    private int capacity;
    private RingBuffer buffer;
    private int tic;
    public GuitarString(double frequency) 
    {
        this.frequency = frequency;
        capacity = (int) Math.ceil(44100.0 / frequency);
        buffer = new RingBuffer(capacity);
        tic = 0;
    }

    public GuitarString(double[] array) 
    {
        capacity = array.length;
        buffer = new RingBuffer(capacity);
        for(int i = 0; i < capacity; i++){
            buffer.add(array[i]);
        }
        tic = 0;
    }

    public void pluck() 
    {
        for(int i = 0; i < capacity; i++){
            buffer.remove();
            buffer.add(Math.random()-.5);
        }
    }

    // advance the simulation one time step
    public void tic() 
    {
        double value1 = buffer.remove();
        double value2 = buffer.peek();
        double ans = ((value1 + value2)/2)*.994;
        buffer.add(ans);
        tic++;
    }

    // return the current sample
    public double sample() 
    {
        return buffer.peek();
    }

    // return number of times tic was called
    public int time() 
    {
        return tic;
    }

    public static void main(String[] args) 
    {
        double[] samples = { .2, .4, .5, .3, -.2, .4, .3, .0, -.1, -.3 };  
        GuitarString testString = new GuitarString(samples);
        for (int i = 0; i < 25; i++) 
        {
            int t = testString.time();
            double sample = testString.sample();
            System.out.printf("%6d %8.4f\n", t, sample);
            testString.tic();
        }
    }
}
