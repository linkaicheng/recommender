package com.cheng.mall.recommend;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

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

import com.cheng.mall.service.ProductService;
import com.cheng.mall.service.UserService;
import com.cheng.mall.service.recommender.RecommenderItemService;

public class RecommendBooks {
	final static int NEIGHBORHOOD_NUM = 2;
	final static int RECOMMENDER_NUM = 10;
	@Resource
	private RecommenderItemService recommenderItemService;
	@Resource
	private UserService userService;
	@Resource
	private ProductService productService;

	public static void main(String[] args) throws IOException, TasteException {

		String file = "datafile/book/rating_nopref.csv";
		DataModel dataModel = new FileDataModel(new File(file));
		final ItemSimilarity itemSimilarity = new EuclideanDistanceSimilarity(dataModel);
		RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
			@Override
			public Recommender buildRecommender(DataModel model) throws TasteException {
				return new GenericBooleanPrefItemBasedRecommender(model, itemSimilarity);
			}
		};

		LongPrimitiveIterator iter = dataModel.getUserIDs();
		while (iter.hasNext()) {
			long uid = iter.nextLong();
			System.out.print("itemEuclideanNoPref =>");
			List<RecommendedItem> list = recommenderBuilder.buildRecommender(dataModel).recommend(uid, RECOMMENDER_NUM);
			System.out.printf("uid:%s,", uid);
			for (RecommendedItem recommendation : list) {

				System.out.printf("(%s,%f)", recommendation.getItemID(), recommendation.getValue());
			}
			System.out.println();
		}

	}
}
