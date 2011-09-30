package org.opeum.persistence;

import javax.ejb.TransactionAttribute;
import javax.inject.Inject;

import org.hibernate.Session;

import audittest.org.opeum.audit.Angel;
import audittest.org.opeum.audit.Demon;
import audittest.org.opeum.audit.Finger;
import audittest.org.opeum.audit.God;
import audittest.org.opeum.audit.Hand;
import audittest.org.opeum.audit.Ring;

@TransactionAttribute
public class AuditController {

	@Inject Session session;
	
	public God captureGod(String name) {
		God god = new God();
		god.setName(name);
		session.persist(god);
		return god;
	}
	
	public void updateGodName(God god, String name) {
		god.setName(name);
		session.flush();
	}

	public void deleteGod(God god) {
		god.markDeleted();
		session.flush();
	}
	
	public Hand addHand(God god, String name) {
		god = (God) session.get(God.class, god.getId());
		Hand hand = new Hand(god);
		hand.setName(name);
		session.flush();
		return hand;
	}

	public Finger addFinger(Hand hand, String name) {
		hand = (Hand) session.get(Hand.class, hand.getId());
		Finger finger = new Finger(hand);
		finger.setName(name);
		session.flush();
		return finger;
	}

	public void updateHand(Hand hand, String string) {
		hand = (Hand) session.get(Hand.class, hand.getId());
		hand.setName(string);
		session.flush();
	}

	public void updateFinger(Finger finger, String string) {
		finger = (Finger) session.get(Finger.class, finger.getId());
		finger.setName(string);
		session.flush();
	}

	public Ring addRing(God god, String name) {
		god = (God) session.get(God.class, god.getId());
		Ring ring = new Ring(god);
		ring.setName(name);
		session.flush();
		return ring;
	}

	public void updateRing(Ring ring, String string) {
		ring = (Ring) session.get(Ring.class, ring.getId());
		ring.setName(string);
		session.flush();
	}

	public void setRingOnFinger(Finger finger, Ring ring) {
		finger = (Finger) session.get(Finger.class, finger.getId());
		ring = (Ring) session.get(Ring.class, ring.getId());
		finger.setRing(ring);
		session.flush();
	}

	public Demon addDemon(God god, String name) {
		god = (God) session.get(God.class, god.getId());
		Demon demon = new Demon(god);
		demon.setName(name);
		session.flush();
		return demon;
	}

	public void updateDemon(Demon demon, String string) {
		demon = (Demon) session.get(Demon.class, demon.getId());
		demon.setName(string);
		session.flush();
	}

	public Angel addAngel(God god, String name) {
		god = (God) session.get(God.class, god.getId());
		Angel angel = new Angel(god);
		angel.setName(name);
		session.flush();
		return angel;
	}

	public void updateAngel(Angel angel, String string) {
		angel = (Angel) session.get(Angel.class, angel.getId());
		angel.setName(string);
		session.flush();
	}

	public void setAngelAndDemo(Angel angel, Demon demon) {
		angel.setDemon(demon);
		session.flush();
	}

}
