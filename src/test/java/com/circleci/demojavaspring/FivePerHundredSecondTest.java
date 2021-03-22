package com.circleci.demojavaspring;

import org.junit.Test;

public class FivePerHundredSecondTest {

    @Test
    public void nullKommaNullFiveSecondTest() throws InterruptedException {
        /*
		5000	fünf Sekunden
		500	fünf /10	Zehntel
		50	fünf /100	Sekunden
		5	fünf /1000	Sekunden, 5 Millisekunden
		*/
		Thread.sleep(50);
    }

}
