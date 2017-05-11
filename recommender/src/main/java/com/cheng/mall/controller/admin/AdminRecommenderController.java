package com.cheng.mall.controller.admin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericBooleanPrefItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cheng.mall.bean.RecommenderItem;
import com.cheng.mall.bean.Record;
import com.cheng.mall.controller.CartController;
import com.cheng.mall.dto.PageDto;
import com.cheng.mall.service.ProductService;
import com.cheng.mall.service.UserService;
import com.cheng.mall.service.recommender.RecommenderItemService;
import com.cheng.mall.service.recommender.RecordService;
import com.cheng.mall.util.Message;

/**
 * 推荐管理，更新推荐结果
 * 
 * @author linkaicheng
 * @date 2017年5月3日 下午2:05:24
 *
 */
@RestController
@RequestMapping("/admin")
public class AdminRecommenderController {
	private Logger logger = Logger.getLogger(CartController.class);
	@Resource
	private RecommenderItemService recommenderItemService;
	@Resource
	private ProductService productService;
	@Resource
	private UserService userService;
	@Resource
	private RecordService recordService;
	final static int NEIGHBORHOOD_NUM = 2;
	final static int RECOMMENDER_NUM = 10;

	/**
	 * 返回所有推荐结果
	 *
	 * @author linkaicheng
	 * @date 2017年5月10日 下午9:07:39
	 * @return
	 *
	 */
	@RequestMapping(value = { "/getRecommenderListAll" }, method = RequestMethod.GET)
	public List<RecommenderItem> getRecommenderList() {
		List<RecommenderItem> recommenderItems = recommenderItemService.findAllRecommenderItems();
		return recommenderItems;
	}

	/**
	 * 返回分页推荐结果
	 * 
	 * @author linkaicheng
	 * @date 2017年5月11日 上午12:00:29
	 * @param request
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 */
	@RequestMapping(value = { "/getRecommenderListPage" }, method = RequestMethod.GET)
	public PageDto<RecommenderItem> getRecommenderListPage(HttpServletRequest request, Integer pageNo,
			Integer pageSize) {
		PageDto<RecommenderItem> pageDto = new PageDto<>();
		List<RecommenderItem> recommenderItems = recommenderItemService.findRecommenderItemsPage(pageNo - 1, pageSize);
		Integer count = recommenderItemService.findCount();
		pageDto.setList(recommenderItems);
		pageDto.setTotalCount(count);
		return pageDto;
	}

	/**
	 * 更新推荐结果并返回
	 * 
	 * @author linkaicheng
	 * @date 2017年5月6日 上午11:13:59
	 * @return
	 * @throws IOException
	 * @throws TasteException
	 *
	 */
	@RequestMapping(value = { "/updateRecommenderList" }, method = RequestMethod.GET)
	public Message updateRecommenderList() throws IOException, TasteException {
		// 根据购买记录生成csv
		List<Record> records = recordService.findAllRecord();
		try {
			FileWriter fw = new FileWriter("datafile/record.csv");
			for (Record record : records) {
				StringBuffer str = new StringBuffer();
				str.append(record.getUser().getUid() + "," + record.getProduct().getPid() + "," + 0 + "\n");
				fw.write(str.toString());
				fw.flush();
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 根据csv文件生成推荐结果
		String file = "datafile/record.csv";
		DataModel dataModel = new FileDataModel(new File(file));
		final ItemSimilarity itemSimilarity = new EuclideanDistanceSimilarity(dataModel);
		RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
			@Override
			public Recommender buildRecommender(DataModel model) throws TasteException {
				return new GenericBooleanPrefItemBasedRecommender(model, itemSimilarity);
			}
		};

		recommenderItemService.deleteAllItem();
		LongPrimitiveIterator iter = dataModel.getUserIDs();
		while (iter.hasNext()) {
			long uid = iter.nextLong();
			System.out.print("itemEuclideanNoPref =>");
			List<RecommendedItem> list = recommenderBuilder.buildRecommender(dataModel).recommend(uid, RECOMMENDER_NUM);
			System.out.printf("uid:%s,", uid);
			for (RecommendedItem recommendation : list) {
				System.out.printf("(%s,%f)", recommendation.getItemID(), recommendation.getValue());
				RecommenderItem recommenderItem = new RecommenderItem();
				recommenderItem
						.setProduct(productService.findProductByPid(((Long) recommendation.getItemID()).intValue()));
				recommenderItem.setRecommenderDate(new Date());
				recommenderItem.setUser(userService.findUserByUid(((Long) uid).intValue()));
				recommenderItem.setScore(recommendation.getValue());
				recommenderItemService.createRecommenderItem(recommenderItem);

			}
			System.out.println();
		}
		Message message = new Message();
		message.setInfo("success");
		System.out.println("=========================" + message.getInfo());
		return message;
	}

}
