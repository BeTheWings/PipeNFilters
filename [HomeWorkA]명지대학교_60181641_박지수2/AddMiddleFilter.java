import java.io.IOException;

public class AddMiddleFilter extends GeneralFilter {

	private StringBuffer StrB = new StringBuffer();
	private StringBuffer StrS = new StringBuffer();

	private byte[] buffer = new byte[2056];

	public void specificComputationForFilter() throws IOException {

		int byte_read = 0;
		int idx = 0; // 432 ��

		while (true) {
			while (byte_read != '\n' && byte_read != -1) {
				if (byte_read != -1) {
					byte_read = in.read();
					buffer[idx] = (byte) byte_read;
					StrB.append((char) buffer[idx]);
					idx++;
				}

			}
			if (StrB.length() == 431) {
				String msg = StrB.toString();
				String[] line = msg.split(System.getProperty("line.separator"));
				for (int i = 0; i < line.length; i++) {
					// 12345 or 23456�� ���� ������� + 17651�� �� �����Ѵ�.
					if (!line[i].contains("12345") && line[i].contains("23456")) {
						// 12345�� �ȵ������ 23456 ok
						line[i] = line[i] + " " + "12345";
						if (!line[i].contains("17651")) {
							// 12345 23456 ������� 17651�ȵ������

							line[i] = line[i] + " " + "17651";
						}
					} else if (line[i].contains("12345") && !line[i].contains("23456")) {
						// 23456�� �ȵ������ 12345 ok

						line[i] = line[i] + " " + "23456";

						if (!line[i].contains("17651")) {

							line[i] = line[i] + " " + "17651";

						}

					} else if (line[i].contains("12345") && line[i].contains("23456") && !line[i].contains("17651")) {
						line[i] = line[i] + " " + "17651";

					}
					StrS.append(line[i] + "\n\r");
				}
			}
			if (byte_read == -1) {

				System.out.println("::filtering is finished;;");
				return;
			}
			idx = 0;
			byte_read = '\0';

			if (StrS.length() != 0) {
				String msg = StrS.toString();
				out.write(msg.getBytes());
			}
		}

	}

}
