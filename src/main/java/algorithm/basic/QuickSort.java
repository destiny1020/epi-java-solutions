package algorithm.basic;

import java.util.Arrays;

/**
 * 快速排序实现。
 * 
 * @author ruixiang
 *
 */
public class QuickSort {

  private int[] arr;

  public QuickSort(int[] arr) {
    this.arr = arr;
  }

  public void sort() {
    coreSort(0, arr.length - 1);
  }

  private void coreSort(int start, int end) {
    int left = start;
    int right = end;
    if (start >= end) {
      return;
    }

    int pivot = arr[start];

    // 开始一次排序
    while (end > start) {
      while (arr[end] >= pivot && end > start) {
        end--;
      }
      if (end > start) {
        swap(end, start);
      }

      while (arr[start] <= pivot && end > start) {
        start++;
      }
      if (end > start) {
        swap(end, start);
      }
    }

    coreSort(left, end - 1);
    coreSort(end + 1, right);
  }

  private void swap(int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public int[] getArr() {
    return arr;
  }

  public static void main(String[] args) {


    QuickSort qs = new QuickSort(new int[] {4, 5, 6, 7, 8, 9, 10, 11, 12, 1, 2, 3});
    qs.sort();
    System.out.println(Arrays.toString(qs.getArr()));

  }

}
