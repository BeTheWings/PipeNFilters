

public class PipesNFiltersMain {

    public static void main(String[] args) {
        try {
            CommonForFilter filter1 = new SourceFilter("Students.txt");
            CommonForFilter filter2 = new SinkFilter("Output.txt");
            CommonForFilter filter3 = new MiddleFilter();
            CommonForFilter filter4 = new AddMiddleFilter();
            
            filter1.connectOutputTo(filter3);
            filter3.connectOutputTo(filter4);
            filter4.connectOutputTo(filter2);
            
            Thread thread1 = new Thread(filter1);
            Thread thread2 = new Thread(filter2);
            Thread thread3 = new Thread(filter3);
            Thread thread4 = new Thread(filter4);
            
            thread1.start();
            thread2.start();
            thread3.start();
            thread4.start();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}