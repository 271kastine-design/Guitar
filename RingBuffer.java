/**
 * This RingBuffer object . . .
 * 
 * @author  
 * @version 
 */
public class RingBuffer 
{
    ArrayQueue<Double> buffer;
    private int size;
    private int capacity;
    public RingBuffer(int capacity)
    {
        this.capacity = capacity;
        size = 0;
        buffer = new ArrayQueue<>();
    }

    public int size()
    {
        return size;
    }
    
    public boolean isEmpty()
    {
        if(size == 0){
            return true;
        }
        return false;
    }
    
    public boolean isFull()
    {
        return size == capacity;
    }
    
    public void add(double value) throws QueueOverflowException
    {
        if(isFull()){
            throw new QueueOverflowException("This exceeds ring buffer capacity!");
        }
        buffer.add(value);
        size++;
    }
    
    public double peek() throws QueueUnderflowException
    {
        if(isEmpty()){
            throw new QueueUnderflowException("The ring buffer is empty!");
        }
        else{
            return buffer.peek();
        }
    }
    
    public double remove() throws QueueUnderflowException
    {
        if(isEmpty()){
            throw new QueueUnderflowException("Ring buffer is empty!");
        }
        double value = buffer.remove();
        size--;
        return value;
    }
    
    // a simple test of the constructor and methods in RingBuffer
    public static void main(String[] args) throws QueueUnderflowException, QueueOverflowException
    {
        int capacity = 100;
        RingBuffer buffer = new RingBuffer(capacity);  
        for (int i = 1; i <= capacity; i++) 
            buffer.add(i);
      
        double t = buffer.remove();
        buffer.add(t);
        System.out.println("Size after wrap-around is " + buffer.size());
        while (buffer.size() >= 2) 
        {
            double x = buffer.remove();
            double y = buffer.remove();
            buffer.add(x + y);
        }
        System.out.println(buffer.peek());
    }

}
