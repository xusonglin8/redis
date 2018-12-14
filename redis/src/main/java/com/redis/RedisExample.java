package com.redis;

import org.redisson.Config;
import org.redisson.Redisson;

import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

/**
 * ����redis�������������ȡconcurrentMap���󡢱����ȡset����ͱ����ȡQueue����
 * @author dell
 *
 */
public class RedisExample {
	public static void main(String[] args) {
		//��ʼ��
		Config config = new Config();
		config.setConnectionPoolSize(10);
		config.addAddress("127.0.0.1:6379");
		Redisson redisson = Redisson.create(config);
		System.out.println("redis���ӳɹ���");

		//1.����concurrentMap,put������ʱ��ͻ�ͬ����redis��
		ConcurrentMap<String,Object> map = redisson.getMap("FirstMap");
		map.put("sss", "��");
		map.put("www","Ů");
		map.put("eee","Ů");

		ConcurrentMap resultMap =redisson.getMap("FirstMap");
		System.out.println("resultMap=" + resultMap.keySet());

		//2.����set����
		Set mySet = redisson.getSet("MySet");
		mySet.add("123");
		mySet.add("456");
		mySet.add("789");

		Set resultSet = redisson.getSet("MySet");
		Iterator<String> it = resultSet.iterator();
		while(it.hasNext()){
			String str = it.next();
			System.out.print(str+" ");
		}
		System.out.println("\n resultSet=" + resultSet.iterator());
		System.out.println("resultSet=" + resultSet.size());

		//3.����Queue����
		Queue myQueue = redisson.getQueue("FirstQueue");
		myQueue.add("����");
		myQueue.add("shanghai");
		myQueue.add("kunming");
		myQueue.peek();
		myQueue.poll();

		Queue resultQueue = redisson.getQueue("FirstQueue");
		System.out.println("resultQueue= " + resultQueue);

		//Redis Flushall ��������������� Redis ������������(ɾ���������ݿ������ key )��


		//�ر�����
		redisson.shutdown();

	}

}