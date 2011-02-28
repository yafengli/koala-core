package effictive.one;

/**
 */
public class NutritionFacts {

	public final int x;//required
	public final int y;//required
	public int z;//oprionnal
	public int u;//oprionnal
	
	/**
	 */
	public static class Builder implements IBuilder<NutritionFacts>{
		private final int x;
		private final int y;
		
		private int z;
		private int u;
		
		/**
		 * Constructor for Builder.
		 * @param x int
		 * @param y int
		 */
		public Builder(int x,int y){
			this.x=x;
			this.y=y;
		}
		/**
		 * Method creatZ.
		 * @param z int
		 * @return Builder
		 */
		public Builder creatZ(int z){
			this.z=z;
			return this;
		}
		/**
		 * Method creatU.
		 * @param u int
		 * @return Builder
		 */
		public Builder creatU(int u){
			this.u=u;
			return this;
		}
		/**
		 * Method build.
		 * @return NutritionFacts
		 * @see effictive.one.IBuilder#build()
		 */	
		public NutritionFacts build(){
			return new NutritionFacts(this);
		}
	}
	/**
	 * Constructor for NutritionFacts.
	 * @param builder Builder
	 */
	private  NutritionFacts(Builder builder){
		this.x=builder.x;
		this.y=builder.y;
		this.z=builder.z;
		this.u=builder.u;
	}
}
