import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class problem_1_solution {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
        System.out.println(Arrays.toString(twoSum2ndSolution(new int[]{2, 7, 11, 15}, 27)));
    }

    // Solution 1
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

// Solution 2

    public static int[] twoSum2ndSolution(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (numMap.containsKey(complement)) {
                return new int[]{numMap.get(complement), i};
            }
            numMap.put(nums[i], i);
            System.out.println("numMap: " + numMap); // to show the map.. how it is working
        }
        return new int[]{};
    }
}
