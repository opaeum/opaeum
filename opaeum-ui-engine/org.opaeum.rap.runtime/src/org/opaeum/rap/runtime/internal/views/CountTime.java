package org.opaeum.rap.runtime.internal.views;

public class CountTime{
	private static final int TIME_WAIT = 500;
	private static int timeWait = TIME_WAIT;
	private CountThread timer = null;
	private IKeepTime notifier;
	public CountTime(IKeepTime n){
		super();
		notifier = n;
	}
	public CountTime(IKeepTime n,int waintingTime){
		this(n);
		timeWait = waintingTime;
	}
	/**
	 * Reset the counter
	 */
	public void newTime(){
		if(timer != null){
			timer.interrupt();
		}
		timer = new CountThread();
		timer.start();
	}
	/**
	 * Private Thread that wait for TIME_WAIT
	 * 
	 * @author cbourdeu
	 * 
	 */
	private class CountThread extends Thread{
		@Override
		public void run(){
			try{
				sleep(timeWait);
				if(!isInterrupted()){
					CountTime.this.notifier.notifyTimeEnd();
				}
			}catch(InterruptedException e){
				// Just finish the thread
			}
		}
	}
}
