public class ArrayQueue<T> implements QueueInterface<T>{
    private T[] array;
    private int size;
    private int front;
    public ArrayQueue(){
        array = (T[]) new Object[4];
        size = 0;
        front = 0;
    }
    public int size(){
        return size;
    }
    public boolean empty(){
        if(size() == 0){
            return true;
        }
        return false;
    }
    public void clear(){
        array = (T[]) new Object[4];
        size = 0;
        front = 0;
    }
    public T add(T item){
        if(size == array.length){
            T[] temp = (T[]) new Object[array.length*2];
            for(int i = 0; i < size; i++){
                temp[i] = array[(front + i) % array.length];
            }
            array = temp;
            front = 0;
        }
        int rear = (front + size) % array.length;
        array[rear] = item;
        size++;
        return item;
    }
    public T remove() throws QueueUnderflowException{
        if(empty()){
            throw new QueueUnderflowException("Queue is empty!");
        }
        T item = array[front];
        array[front] = null;
        front = (front + 1) % array.length;
        size--;

        if(array.length > 4 && size <= array.length / 4){
            T[] temp = (T[]) new Object[array.length / 2];
            for(int i = 0; i < size; i++){
                temp[i] = array[(front + i) % array.length];
            }
            array = temp;
            front = 0;
        }
        return item;
    }
    public T peek() throws QueueUnderflowException{
        if(empty()){
            throw new QueueUnderflowException("Queue is empty!");
        }
        return array[front];
    }
}