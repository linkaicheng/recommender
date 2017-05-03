package com.cheng.mall.recommend.book;

import java.io.IOException;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

/**
 * 
 * @author linkc 选出"评估推荐器"验证得分较高的算法
 */
public class BookEvaluator {

	final static int NEIGHBORHOOD_NUM = 2;
	final static int RECOMMENDER_NUM = 3;

	public static void main(String[] args) throws TasteException, IOException {
		String file = "datafile/book/rating.csv";
		DataModel dataModel = RecommendFactory.buildDataModel(file);
		// userEuclidean(dataModel);
		// userLoglikelihood(dataModel);
		// userEuclideanNoPref(dataModel);
		// itemEuclidean(dataModel);
		// itemLoglikelihood(dataModel);
		System.out.println("====================================");
		itemEuclideanNoPref(dataModel);
		// slopeOne(dataModel);
	}

	/**
	 * 基于欧氏距离的usercf
	 * 
	 * @param dataModel
	 * @return
	 * @throws TasteException
	 * @throws IOException
	 */
	public static RecommenderBuilder userEuclidean(DataModel dataModel) throws TasteException, IOException {
		System.out.println("userEuclidean");
		UserSimilarity userSimilarity = RecommendFactory.userSimilarity(RecommendFactory.SIMILARITY.EUCLIDEAN,
				dataModel);
		UserNeighborhood userNeighborhood = RecommendFactory.userNeighborhood(RecommendFactory.NEIGHBORHOOD.NEAREST,
				userSimilarity, dataModel, NEIGHBORHOOD_NUM);
		RecommenderBuilder recommenderBuilder = RecommendFactory.userRecommender(userSimilarity, userNeighborhood,
				true);

		RecommendFactory.evaluate(RecommendFactory.EVALUATOR.AVERAGE_ABSOLUTE_DIFFERENCE, recommenderBuilder, null,
				dataModel, 0.7);
		RecommendFactory.statsEvaluator(recommenderBuilder, null, dataModel, 2);
		return recommenderBuilder;
	}

	/**
	 * 基于对数自然的usercf
	 * 
	 * @param dataModel
	 * @return
	 * @throws TasteException
	 * @throws IOException
	 */
	public static RecommenderBuilder userLoglikelihood(DataModel dataModel) throws TasteException, IOException {
		System.out.println("userLoglikelihood");
		UserSimilarity userSimilarity = RecommendFactory.userSimilarity(RecommendFactory.SIMILARITY.LOGLIKELIHOOD,
				dataModel);
		UserNeighborhood userNeighborhood = RecommendFactory.userNeighborhood(RecommendFactory.NEIGHBORHOOD.NEAREST,
				userSimilarity, dataModel, NEIGHBORHOOD_NUM);
		RecommenderBuilder recommenderBuilder = RecommendFactory.userRecommender(userSimilarity, userNeighborhood,
				true);

		RecommendFactory.evaluate(RecommendFactory.EVALUATOR.AVERAGE_ABSOLUTE_DIFFERENCE, recommenderBuilder, null,
				dataModel, 0.7);
		RecommendFactory.statsEvaluator(recommenderBuilder, null, dataModel, 2);
		return recommenderBuilder;
	}

	/**
	 * 基于欧氏距离，没有评分（两列数据）的usercf
	 * 
	 * @param dataModel
	 * @return
	 * @throws TasteException
	 * @throws IOException
	 */
	public static RecommenderBuilder userEuclideanNoPref(DataModel dataModel) throws TasteException, IOException {
		System.out.println("userEuclideanNoPref");
		UserSimilarity userSimilarity = RecommendFactory.userSimilarity(RecommendFactory.SIMILARITY.EUCLIDEAN,
				dataModel);
		UserNeighborhood userNeighborhood = RecommendFactory.userNeighborhood(RecommendFactory.NEIGHBORHOOD.NEAREST,
				userSimilarity, dataModel, NEIGHBORHOOD_NUM);
		RecommenderBuilder recommenderBuilder = RecommendFactory.userRecommender(userSimilarity, userNeighborhood,
				false);

		RecommendFactory.evaluate(RecommendFactory.EVALUATOR.AVERAGE_ABSOLUTE_DIFFERENCE, recommenderBuilder, null,
				dataModel, 0.7);
		RecommendFactory.statsEvaluator(recommenderBuilder, null, dataModel, 2);
		return recommenderBuilder;
	}

	public static RecommenderBuilder itemEuclidean(DataModel dataModel) throws TasteException, IOException {
		System.out.println("itemEuclidean");
		ItemSimilarity itemSimilarity = RecommendFactory.itemSimilarity(RecommendFactory.SIMILARITY.EUCLIDEAN,
				dataModel);
		RecommenderBuilder recommenderBuilder = RecommendFactory.itemRecommender(itemSimilarity, true);

		RecommendFactory.evaluate(RecommendFactory.EVALUATOR.AVERAGE_ABSOLUTE_DIFFERENCE, recommenderBuilder, null,
				dataModel, 0.7);
		RecommendFactory.statsEvaluator(recommenderBuilder, null, dataModel, 2);
		return recommenderBuilder;
	}

	public static RecommenderBuilder itemLoglikelihood(DataModel dataModel) throws TasteException, IOException {
		System.out.println("itemLoglikelihood");
		ItemSimilarity itemSimilarity = RecommendFactory.itemSimilarity(RecommendFactory.SIMILARITY.LOGLIKELIHOOD,
				dataModel);
		RecommenderBuilder recommenderBuilder = RecommendFactory.itemRecommender(itemSimilarity, true);

		RecommendFactory.evaluate(RecommendFactory.EVALUATOR.AVERAGE_ABSOLUTE_DIFFERENCE, recommenderBuilder, null,
				dataModel, 0.7);
		RecommendFactory.statsEvaluator(recommenderBuilder, null, dataModel, 2);
		return recommenderBuilder;
	}

	public static RecommenderBuilder itemEuclideanNoPref(DataModel dataModel) throws TasteException, IOException {
		System.out.println("========================itemEuclideanNoPref====================");
		ItemSimilarity itemSimilarity = RecommendFactory.itemSimilarity(RecommendFactory.SIMILARITY.EUCLIDEAN,
				dataModel);
		RecommenderBuilder recommenderBuilder = RecommendFactory.itemRecommender(itemSimilarity, false);

		RecommendFactory.evaluate(RecommendFactory.EVALUATOR.AVERAGE_ABSOLUTE_DIFFERENCE, recommenderBuilder, null,
				dataModel, 0.7);
		RecommendFactory.statsEvaluator(recommenderBuilder, null, dataModel, 2);
		return recommenderBuilder;
	}

	public static RecommenderBuilder slopeOne(DataModel dataModel) throws TasteException, IOException {
		System.out.println("slopeOne");
		RecommenderBuilder recommenderBuilder = RecommendFactory.slopeOneRecommender();

		RecommendFactory.evaluate(RecommendFactory.EVALUATOR.AVERAGE_ABSOLUTE_DIFFERENCE, recommenderBuilder, null,
				dataModel, 0.7);
		RecommendFactory.statsEvaluator(recommenderBuilder, null, dataModel, 2);
		return recommenderBuilder;
	}

}
