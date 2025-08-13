package com.zhouyu.study.week1.day1;

/**
 * 不可变对象
 * 使用final修饰类
 * 并使用final修饰成员变量
 *
 * 两个对象相互运算时，生成新的对象，而不改变原有对象
 */
public final class ImmutableObject {
    private final int amount;
    private final String currency;

    public ImmutableObject(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public int getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public ImmutableObject add(ImmutableObject immutableObject){
        if(!this.currency.equalsIgnoreCase(immutableObject.currency)){
            throw new IllegalArgumentException("Currency must be same");
        }
        return new ImmutableObject(this.amount + immutableObject.amount, this.currency);
    }

    public static void main(String[] args) {
        ImmutableObject immutableObject = new ImmutableObject(1, "RMB");
        System.out.println(immutableObject.getAmount());
        ImmutableObject immutableObject1 = new ImmutableObject(2, "RMB");
    }
}
