package lambda;

public class LambdaEx4 {
	public static void main(String[] args) {
		
		Lambda4 lambda=(x, y) -> x > y ? x : y;
		System.out.println(lambda.max(150,100));
		
		Lambda5 lambda5=(x, y) -> x > y ? y : x;
		System.out.println(lambda5.min(150,100));
		
//		lambda=(x)->{
//			int i=10;
//			System.out.println(i*x);
//		};
//		lambda.method(100);
	}

}
