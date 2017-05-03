package com.cheng.mall.recommend;

import javax.annotation.Resource;

import com.cheng.mall.service.recommender.RecordService;

public class CreateCsv {
	@Resource
	private RecordService recordService;

	public static void main(String[] args) {

		// List<Record> records = recordService.findAllRecord();
		// System.out.println(records);

		// try {
		// FileWriter fw = new FileWriter("datafile/new.csv");
		// // String header = "trdate,hpr,lpr,tradeVolumn\n";
		// // fw.write(header);
		// for (Record record : records) {
		// StringBuffer str = new StringBuffer();
		// str.append(record.getUser().getUid() + "," +
		// record.getProduct().getPid() + "\n");
		// fw.write(str.toString());
		// fw.flush();
		// }
		// fw.close();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}

}
