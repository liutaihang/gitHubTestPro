package liu.DubboProvider;

import java.util.ArrayList;
import java.util.List;

import liu.common.TestInterface;
import liu.common.po.Customer;

public class TestInterfaceImpl implements TestInterface{

	@Override
	public String saySomething(String content) {
		return "TestInterfaceImpl--->say:"+content;
		
	}

	@Override
	public List<String> getList() {
		List<String> lists = new ArrayList<>();
		lists.add(new Customer("小苟", "man", 175).toString());
		lists.add(new Customer("小朱", "man", 170).toString());
		lists.add(new Customer("小虹", "lady", 162).toString());
		
		return lists;
	}

}
