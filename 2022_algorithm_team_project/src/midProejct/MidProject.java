package midProejct;

import java.util.HashMap;
import java.util.Random;

class SLP {
	private int diceCnt, curPos;

	public SLP() {
		diceCnt = 0;
		curPos = 1;
	}

	public void process(HashMap<Integer, Integer> snakes, HashMap<Integer, Integer> ladders) {
		Random random = new Random();
		int diceNum;

		while (true) {
			diceNum = random.nextInt(6) + 1;
			curPos += diceNum;
			diceCnt++;
			System.out.print(curPos - diceNum + "에서 " + "주사위 값이 " + diceNum + "이 나와 ");
			if (snakes.containsKey(curPos))
				curPos = snakes.get(curPos);
			else if (ladders.containsKey(curPos))
				curPos = ladders.get(curPos);
			System.out.println(curPos + "(으)로 이동했습니다.");
			if (curPos >= 100)
				break;
		}
	}

	public int getDiceCnt() {
		return diceCnt;
	}
}

public class MidProject {

	public static void main(String[] args) {
		HashMap<Integer, Integer> snakes = new HashMap<>(), ladders = new HashMap<>();

		snakes.put(56, 4);
		snakes.put(88, 33);
		snakes.put(97, 59);

		ladders.put(8, 55);
		ladders.put(19, 76);
		ladders.put(32, 92);

		int totalDiceCnt = 0, totalTryCnt = 0;
		SLP slp;

		while (totalTryCnt < 1) {
			totalTryCnt++;
			slp = new SLP();
			slp.process(snakes, ladders);
			totalDiceCnt += slp.getDiceCnt();
		}
		System.out.println(totalTryCnt + "번 시도한 결과, 총 주사위가 던져진 횟수는 " + totalDiceCnt + "번 이었고, 평균은 "
				+ String.format("%.2f", totalDiceCnt / (double) (totalTryCnt)) + "입니다.");
	}

}
