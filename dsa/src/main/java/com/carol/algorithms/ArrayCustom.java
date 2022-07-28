package com.carol.algorithms;




import java.util.Arrays;

/**
 * 基本数据结构数组演示
 * 数组特点：
 * 1.固定大小
 * 2.插入删除O(n)
 * 3.支持随机查找（通过下标进行访问）
 *
 * 实现样例
 * 1.实现一个支持动态扩容的数组 演示数组固定大小，扩容需要重新申请空间，并且copy原来数据
 * 2.实现一个大小固定的有序数组 进行排序时候插入数据，需要进行移动数据，时间复杂度O（n）
 * 3.支持动态增删改操作
 * 4.实现两个有序数组合并为一个有序数组
 * @author Carol
 * @date 2022/5/30
 * @since 1.0.0
 */

public class ArrayCustom {


    /**
     * 支持动态扩容的数组
     * 1.初始化一个数组
     * 2.数组插入判断数组是否满,满了需要进行扩容
     * 3.扩容方式，申请一个原数组两倍大小的数组，将原数组copy入新数组
     */
    public static class DynamicArray {
        /**
         * 数组初始化大小
         */
        private final int DEFAULT_LENGTH = 2;
        /**
         * 数组增长倍数
         */
        private final int GROW_LEVEL = 2;
        /**
         * 当前数组大小
         */
        private int length;

        /**
         * 当前数组中元素数量
         */
        private int size;

        int[] nums;

        public static void main(String[] args) {
            DynamicArray array = new DynamicArray();
            System.out.println(array);
            array.add(1);
            array.add(2);
            System.out.println(array);
            array.add(3);
            System.out.println(array);
        }

        DynamicArray() {
            nums = new int[DEFAULT_LENGTH];
            length = DEFAULT_LENGTH;
            size = 0;
        }

        public void add(int num) {
            //如果当前已经满，进行扩容操作
            if (size + 1 > length) {
                this.growLength();
            }
            //扩容之后直接存入数据
            nums[size] = num;
            ++ size;
        }

        /**
         * 扩容操作
         */
        public void growLength() {
            int[] temp = nums;
            length *= GROW_LEVEL;
            nums = new int[length];
            for (int i = 0 ; i < temp.length ; i++) {
                nums[i] = temp[i];
            }
        }

        @Override
        public String toString() {
            return "DynamicArray{" +
                    "DEFAULT_SIZE=" + DEFAULT_LENGTH +
                    ", GROW_LEVEL=" + GROW_LEVEL +
                    ", length=" + length +
                    ", size=" + size +
                    ", nums=" + Arrays.toString(nums) +
                    '}';
        }
    }

    /**
     * 实现一个大小固定的有序数组
     * 1.初始化一个数字，固定大小
     * 2.插入一个值，值需要进行排序
     */
    public static class SortedArray {
        private final int DEFAULT_LENGTH = 10;
        private int size;
        private int length;
        int[] nums;
        SortedArray() {
            nums = new int[DEFAULT_LENGTH];
            length = DEFAULT_LENGTH;
            size = 0;
        }
        SortedArray(int initLength) {
            nums = new int[initLength];
            size = 0;
            length = initLength;
        }

        public static void main(String[] args) {
            SortedArray array = new SortedArray(2);
            System.out.println(array);
            System.out.println( array.add(2));
            System.out.println(array.add(3));
            System.out.println(array);
            System.out.println(array.add(5));
        }

        /**
         * 插入一个值，并返回插入下标
         * @return
         */
        public int add(int num) {
            if (size == length) {
                //不能再加入
                return -1;
            }
            int index = this.binarySearch(num, 0, size - 1);
            //需要移动并插入
            for (int i = size ; i > index ; i --) {
                nums[i] = nums[i - 1];
            }
            nums[index] = num;
            ++ size;
            return index;
        }

        /**
         * 查找num的插入位置
         * @param num
         * @param start
         * @param end
         * @return
         */
        public int binarySearch(int num, int start, int end) {
            if (start > end) {
                return start;
            }
            int mid = start + (end - start) >> 1;
            if (nums[mid] == num) {
                return mid;
            }
            if (nums[mid] > num) {
                return binarySearch(num, start, mid - 1);
            }
            return binarySearch(num, mid + 1, end);
        }

        @Override
        public String toString() {
            return "SortedArray{" +
                    "DEFAULT_LENGTH=" + DEFAULT_LENGTH +
                    ", size=" + size +
                    ", length=" + length +
                    ", nums=" + Arrays.toString(nums) +
                    '}';
        }
    }
}