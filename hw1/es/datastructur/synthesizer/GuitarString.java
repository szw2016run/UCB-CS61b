package es.datastructur.synthesizer;

//Note: This file will not compile until you complete task 1 (BoundedQueue).
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        int capacity =  (int) Math.round(SR / frequency);
        buffer = new ArrayRingBuffer<>(capacity);
        while (!buffer.isFall()) {
            buffer.enqueue(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        while(!buffer.isEmpty()){
            buffer.dequeue();
        }

        double[] nonDuplicatedRandomDouble = new double[buffer.capacity()];
        boolean isDuplicate = false;
        int count = 0;
        while (count < nonDuplicatedRandomDouble.length) {
            double r = Math.random() - 0.5;
            for (int i = 0; i < nonDuplicatedRandomDouble.length; i++) {
                if (r == nonDuplicatedRandomDouble[i]) {
                    isDuplicate = true;
                    break;
                }
                if (!isDuplicate) {
                    nonDuplicatedRandomDouble[count] = r;
                    count += 1;
                    buffer.enqueue(r);
                }
            }
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        double oldFront = buffer.dequeue();
        double doubleToEnqueue = (buffer.peek() + oldFront) / 2 * DECAY;
        buffer.enqueue(doubleToEnqueue);

    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }

}
// TODO: Remove all comments that say TODO when you're done.
