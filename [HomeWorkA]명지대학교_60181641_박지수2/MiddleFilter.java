
/**
 * Copyright(c) 2013 All rights reserved by JU Consulting
 */

import java.io.IOException;

/**
 * @author Jungho Kim
 * @date 2019
 * @version 1.1
 * @description �엯�젰媛믪쑝濡� 諛쏆� byte�뱾�쓣 �븘臾� 湲곕뒫�룄 �븯吏� �븡怨� 洹몃�濡� �쟾�넚�븯�뒗 湲곕뒫�쓣
 *              �븳�떎.
 */
public class MiddleFilter extends GeneralFilter {

	@Override
	public void specificComputationForFilter() throws IOException {
		int byte_read = 0;
		int checkBlank = 4;
		int numOfBlank = 0;
		int idx = 0;
		byte[] buffer = new byte[64];
		boolean isCS = false;

		while (true) {
			while (byte_read != '\n' && byte_read != -1) {
				byte_read = in.read();
				if (byte_read == ' ') {
					numOfBlank++;
				}
				if (byte_read != -1) {
					buffer[idx++] = (byte) byte_read;
				}
				if (numOfBlank == checkBlank && buffer[idx - 3] == 'C' && buffer[idx - 2] == 'S') {
					isCS = true;
				}
			}

			if (isCS == true) {
				for (int i = 0; i < idx; i++) {
					out.write(buffer[i]);
				}
				isCS = false;
			}
			if (byte_read == -1) {
				System.out.println("::filtering is finished");
				return;
			}
			idx = 0;
			numOfBlank = 0;
			byte_read = '\0';
		}
	}

}
