import java.util.ArrayList;
import java.util.Random;

public class ZombieDiceBucket {
    private ArrayList<ZombieDie> bucket;

    public ZombieDiceBucket(){
        bucket = new ArrayList<>();
        loadBucket();
    }

    public void loadBucket(){
        bucket.clear();
        for (int i = 0; i < 6; i++){
            bucket.add(new GreenZombieDie());
        }
        for (int i = 0; i < 4; i++){
            bucket.add(new YellowZombieDie());
        }
        for (int i = 0; i < 3; i++){
            bucket.add(new RedZombieDie());
        }
    }

    public ZombieDie draw(){
        if (bucket.isEmpty()){
            return null;
        } else {
            Random indexer = new Random();
            int index = indexer.nextInt(bucket.size());
            return bucket.remove(index);
        }
    }
}
