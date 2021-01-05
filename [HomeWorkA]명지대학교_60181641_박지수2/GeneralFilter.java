
/**
 * Copyright(c) 2019 All rights reserved by JU Consulting
 */

import java.io.EOFException;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @author Jungho Kim
 * @date 2019
 * @version 1.1
 * @description CommonForFilter �씤�꽣�럹�씠�뒪瑜� 援ы쁽�븳 異붿긽�겢�옒�뒪. 紐⑤뱺 Filter�뒗 蹂�
 *              Filter瑜� �긽�냽諛쏆븘 援ы쁽�븯寃� �맂�떎.
 */
public abstract class GeneralFilter implements CommonForFilter {
	protected PipedInputStream in = new PipedInputStream();
	protected PipedOutputStream out = new PipedOutputStream();

	/**********
	 * Implementation of public methods defined in CommonForFilter interface
	 ************/

	public void connectOutputTo(CommonForFilter nextFilter) throws IOException {
		out.connect(nextFilter.getPipedInputStream());
	}

	public void connectInputTo(CommonForFilter previousFilter) throws IOException {
		in.connect(previousFilter.getPipedOutputStream());
	}

	public PipedInputStream getPipedInputStream() {
		return in;
	}

	public PipedOutputStream getPipedOutputStream() {
		return out;
	}

	/**********
	 * Implementation of public methods defined in Runnable interface
	 ************/

	public void run() {
		try {
			specificComputationForFilter();
		} catch (IOException e) {
			if (e instanceof EOFException)
				return;
			else
				System.out.println(e);
		} finally {
			try {
				out.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/********** Implementation of protected methods ************/

	/**
	 * Filter媛� �옉�룞�쓣 �젙吏��븯湲� �쟾�뿉 Input/Output Stream port瑜� �떕�뒗�떎.
	 */
	protected void closePorts() {
		try {
			in.close();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/********** Abstract method that should be implemented ************/

	/**
	 * 媛� Filter�쓽 �듅�닔�븳 湲곕뒫�씠 �씠怨녹뿉 湲곕줉�릺硫�, �씠 硫붿냼�뱶�뒗 run()�뿉 �쓽�빐 �샇異쒕맖�쑝濡쒖뜥
	 * Filter媛� 湲곕뒫�븯寃� �맂�떎.
	 * 
	 * @throws IOException
	 */
	abstract public void specificComputationForFilter() throws IOException;
}
