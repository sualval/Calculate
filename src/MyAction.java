import java.util.concurrent.RecursiveTask;

class MyAction extends RecursiveTask<Integer> {
    private int[] list;
    private int start;
    private int end;
    private int middle;

    public MyAction(int[] list, int start, int end) {
        this.list = list;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        final int diff = end - start;
        return switch (diff) {
            case 0 -> 0;
            case 1 -> list[start];
            case 2 -> list[start] + list[start + 1];
            default -> forkTasks();
        };
    }

    private int forkTasks() {
        middle = (end - start) / 2 + start;
        MyAction task1 = new MyAction(list, start, middle);
        MyAction task2 = new MyAction(list, middle, end);
        invokeAll(task1, task2);
        return task1.join() + task2.join();

    }
}
