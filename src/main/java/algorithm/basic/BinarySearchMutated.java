package algorithm.basic;

/**
 * @formatter:off
 * 二分搜索算法的一种变体。需要判断诸如数组:
 * [5, 6, 7, 8, 9, 1, 2, 3, 4] 中是否存在某个元素
 * 
 * 变体体现在：
 * 有序数组被拆分为了两段有序的子数组，比如上面例子中的5-9和1-4
 * 
 * 算法设计思路：
 * 1. 首先寻找出分界点，比如元素1的位置，这个元素也是整个数组中最小的元素。这个元素的特点：比左边和右边的元素都小
 * 2. 如果需要搜索的数字比1小或者比1左边的元素9还要大，那么可以直接给出结论：不存在这么一个元素
 * 3. 如果需要搜索的数字在1和最后一个元素4之间，那么在此区间内搜索，否则在第一个区间内搜索
 * 
 * @formatter:on
 * 
 * @author ruixiang
 *
 */
public class BinarySearchMutated {

  private int[] arr;
  private int splitIdx;

  public BinarySearchMutated(int[] arr) {
    this.arr = arr;
    this.splitIdx = detectSplitIndex();
  }

  private int detectSplitIndex() {
    int length = arr.length;
    int left = 0, right = length - 1;
    int mid = (right - left) / 2;
    while (left < mid && mid < right) {
      // 特征就是分界点小于左边的元素，同时也小于右边的元素
      if (arr[mid - 1] < arr[mid] && arr[mid] < arr[mid + 1]) {
        if (arr[0] < arr[mid]) {
          // 在前半段
          left = mid;
        } else {
          // 在后半段
          right = mid;
        }
        mid = left + (right - left) / 2;
      } else {
        // 确保返回的是右边子序列的首个元素
        if (mid + 1 <= length - 1 && arr[mid] > arr[mid + 1]) {
          mid += 1;
        }
        return mid;
      }
    }

    return mid;
  }

  private int binaryCore(int target, int start, int end) {
    while (start <= end) {
      int mid = start + (end - start) / 2;
      if (arr[mid] < target) {
        start = mid + 1;
      } else if (arr[mid] > target) {
        end = mid - 1;
      } else {
        return mid;
      }
    }

    return -1;
  }

  public int search(int target) {
    if (target < arr[splitIdx] || target > arr[splitIdx - 1]) {
      return -1;
    }

    if (target >= arr[splitIdx] && target <= arr[arr.length - 1]) {
      return binaryCore(target, splitIdx, arr.length - 1);
    } else {
      return binaryCore(target, 0, splitIdx - 1);
    }
  }

  public static void main(String[] args) {

    BinarySearchMutated bs =
        new BinarySearchMutated(new int[] {4, 5, 6, 7, 8, 9, 10, 11, 12, 1, 2, 3});

    System.out.println(bs.search(5));
  }

}
