package midProejct;

import java.util.HashMap;
import java.util.Random;

class SLP { // Snake and Ladder Problem
	private int diceCnt, curPos, repeatNum; // diceCnt: 주사위를 던진 횟수, curPos: 현재 위치(좌표), repeatNum: 반복 횟수
	private HashMap<Integer, Integer> snakes, ladders; // snakes: 뱀이 머리와 꼬리, ladders: 사다리의 위아래

	// 초기값 설정 (repeatNum 지정 X)
	public SLP(HashMap<Integer, Integer> snakes, HashMap<Integer, Integer> ladders) {
		diceCnt = 0;
		repeatNum = 1;
		this.snakes = snakes;
		this.ladders = ladders;
	}

	// 초기값 설정 (repeatNum 지정 O)
	public SLP(HashMap<Integer, Integer> snakes, HashMap<Integer, Integer> ladders, int repeatNum) {
		diceCnt = 0;
		this.repeatNum = repeatNum;
		this.snakes = snakes;
		this.ladders = ladders;
	}

	public void process() { // 처리를 위해 불리는 함수
		for (int i = 0; i < repeatNum; i++)
			play();
	}

	private void play() { // 실제 처리
		Random random = new Random();
		curPos = 1; // 현재 위치 1로 초기화
		int diceNum; // 함수 내 지역변수, 현재 주사위 눈금 수

		do {
			diceNum = random.nextInt(6) + 1; // 1 ~ 6 중 무작위 수 초기화
			curPos += diceNum; // 현재 위치는 주사위 눈금 수만큼 더한 위치로 설정
			diceCnt++; // 던진 횟수 1 추가
			System.out.print(curPos - diceNum + "에서 " + "주사위 값이 " + diceNum + "이 나와 ");

			// 뱀과 사다리의 위치는 동일하지 X
			if (snakes.containsKey(curPos)) // 현재 위치가 뱀의 머리라면? => 뱀의 꼬리로 이동
				curPos = snakes.get(curPos);
			else if (ladders.containsKey(curPos)) // 현재 위치가 아랫쪽 사다리라면? => 사다리의 윗쪽으로 이동
				curPos = ladders.get(curPos);

			System.out.println(curPos + "(으)로 이동했습니다.");
		} while (curPos < 100); // 현재 위치가 100 이상인 경우 종료
	}

	public double getAvgDiceCnt() {
		return diceCnt / (double) repeatNum;
	}

	public String toString() {
		return repeatNum + "번 시도한 결과, 총 주사위가 던져진 횟수는 " + diceCnt + "번 이었고, 평균은 "
				+ String.format("%.2f", getAvgDiceCnt()) + "입니다.";
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

		SLP slp = new SLP(snakes, ladders, 30);
		slp.process();
		System.out.println(slp);
	}

}
