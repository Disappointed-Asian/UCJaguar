import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import java.util.List;

public class CrossMath {
    public static void main(String arg[]){
        int num[] = {1,2,3,4,5,6,7,8,9};
        Integer[] trackNum = new Integer[9];
        ArrayList<Integer> track = new ArrayList<>(Arrays.asList(trackNum));
        int results = 0;

        for(int n: num){
            track.set(0, n);
            for(int o: num){
                track.set(1, o);
                
                for(int p: num){
                    track.set(2, p);
                    

                    for(int q: num){
                        track.set(3, q);
                        
                        for(int r: num){
                            track.set(4, r);

                            for(int s: num){
                                track.set(5, s);
                                
                                for(int t: num){
                                    track.set(6, t);
                                    

                                    for(int u: num){
                                        track.set(7, u);
                                        
                                        for(int v: num){
                                             track.set(8, v);
                                            
                                            int firstComp = 0;
                                            int secondComp = 0;

                                            if(13*track.get(1)%track.get(2)!=0) continue;
                                            else firstComp = 13*track.get(1)/track.get(2);

                                            if(track.get(6) * track.get(7)%track.get(8)!=0) continue;
                                            else secondComp = track.get(6) * track.get(7)/track.get(8);

                                            results = track.get(0) + firstComp +
                                            track.get(3)+ (12 * track.get(4)) - track.get(5) - 11 +
                                            secondComp - 10;

                                            if (results == 66){
                                                int exit = 0;
                                               for(int i: track){
                                                    if(Collections.frequency(track, i) > 1){
                                                        exit = 1;
                                                        break;
                                                    }
                                                }
                                                if(exit == 0) System.out.println("Results: " + track + " = " + results);
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
