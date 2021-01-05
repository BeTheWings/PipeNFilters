import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;



public interface CommonForFilter extends Runnable{

    public void connectOutputTo(CommonForFilter filter) throws IOException;
    
 
    public void connectInputTo(CommonForFilter filter) throws IOException;
    

    public PipedInputStream getPipedInputStream();
    
    public PipedOutputStream getPipedOutputStream();
}
