/**  
 * @Title: AddCarView.java  
 * @Package com.yichao.views  
 * @Description: TODO(��һ�仰�������ļ���ʲô)  
 * @author Administrator  
 * @date 2017��12��14��  
 * @version V1.0  
 */  
package com.yichao.views;

import com.yichao.bean.Car;

/**  
 * @ClassName: AddCarView  
 * @Description: TODO(������һ�仰��������������)  
 * @author Administrator  
 * @date 2017��12��14��  
 *    
 */
public class AddCarView extends View {

	private static int step;
	/* (�� Javadoc)  
	 * <p>Title: showView</p>  
	 * <p>Description: </p>  
	 * @return  
	 * @see com.yichao.views.View#showView()  
	 */
	@Override
	public View showView() {
		System.out.println("=====================================");
		System.out.println("��ֱ�����������Ϣ:");
		step = 1;
		int carBrandId = 0;
		int carTypeId = 0;
		String carBrand = "";
		String carType = "";
		String carName = "";
		String carRemark = "";
		Double carPrice = 0.0;
		Double carLendPrice = 0.0;
		int carStatus = 0;
		while(step != 0) {
			switch(step) {
			case 1:
				step1();
				carBrandId = iT.getInt();
				if(carBrandId > 5 || carBrandId <= 0) {
					step = 1;
					System.out.println("�޴˱��!");
				}else {
					step = 2;
					carBrand = getCarBrand(carBrandId);
				}
				break;
			case 2:
				step2();
				carTypeId = iT.getInt();
				if(carTypeId > 4 || carTypeId <= 0) {
					step = 2;
					System.out.println("�޴˱��!");
				}else {
					step = 3;
					carType = getCarType(carTypeId);
				}
				break;
			case 3:
				System.out.println("-------------------------------------");
				System.out.print("��������:");
				carName = iT.getString();
				System.out.println();
				step = 4;
				break;
			case 4:
				System.out.println("-------------------------------------");
				System.out.print("��Ҫ:");
				carRemark = iT.getString();
				System.out.println();
				step = 5;
				break;
			case 5:
				System.out.println("-------------------------------------");
				System.out.print("�����۸�:");
				carPrice = iT.getDouble();
				System.out.println();
				if(carPrice < 0) {
					System.out.println("�۸���Ϊ����!");
					step = 5;
				}else {
					step = 6;
				}
				break;
			case 6:
				System.out.println("-------------------------------------");
				System.out.print("ÿ�����:");
				carLendPrice = iT.getDouble();
				System.out.println();
				if(carLendPrice < 0) {
					System.out.println("�۸���Ϊ����!");
					step = 6;
				}else {
					step = 7;
				}
				break;
			case 7:
				System.out.println("-------------------------------------");
				System.out.print("�Ƿ��ϼ�(1:�ϼ�,0:�¼�):");
				carStatus = iT.getInt();
				System.out.println();
				if(carStatus < 0 || carStatus > 1) {
					System.out.println("������1��0!");
					step = 7;
				}else {
					step = 0;
				}
			default:
				break;
			}
		}
		Car car = new Car();
		car.setCarBrandId(carBrandId);
		car.setCarBrand(carBrand);
		car.setCarTypeId(carTypeId);
		car.setCarType(carType);
		car.setCarName(carName);
		car.setCarPrice(carPrice);
		car.setCarLendPrice(carLendPrice);
		car.setCarRemark(carRemark);
		car.setCarStatus(carStatus);
		boolean isSuccess = ab.addCar(car);
		if(isSuccess) {
			System.out.println("��ӳɹ�!");
		}else {
			System.out.println("���ʧ��!");
		}
				
				
		
		return mView = new AdminMenuView();
	}

	private String getCarType(int carTypeId) {
		String carType = "";
		switch(carTypeId) {
		case 1:
			carType = "������";
			break;
		case 2:
			carType = "������";
			break;
		case 3:
			carType = "SUV";
			break;
		case 4:
			carType = "��Ӣ��";
			break;		
		default:
			break;
		}
		return carType;
	}

	private void step2() {
		int carTypeId;
		System.out.println("-------------------------------------");
		System.out.println("��������:");
		System.out.println("���ͱ��\t������");
		System.out.println("1\t������");
		System.out.println("2\t������");
		System.out.println("3\tSUV");
		System.out.println("4\t��Ӣ��");
		System.out.print("��ѡ�����ͱ��:");
		carTypeId = iT.getInt();
		System.out.println();
	}

	private String getCarBrand(int carBrandId) {
		String carBrand = "";
		switch(carBrandId) {
		case 1:
			carBrand = "����";
			break;
		case 2:
			carBrand = "����";
			break;
		case 3:
			carBrand = "�µ�";
			break;
		case 4:
			carBrand = "����";
			break;
		case 5:
			carBrand = "����";
			break;
		default:
			break;
		}
		return carBrand;
	}

	private void step1() {
		System.out.println("-------------------------------------");
		System.out.println("Ʒ������:");
		System.out.println("Ʒ�Ʊ��\tƷ����");
		System.out.println("1\t����");
		System.out.println("2\t����");
		System.out.println("3\t�µ�");
		System.out.println("4\t����");
		System.out.println("5\t����");
		System.out.print("��ѡ��Ʒ�Ʊ��:");
		int carBrandId = iT.getInt();
		System.out.println();
	}

}
