	// Class to represent a job
	private static class Job
	{
	    int start, finish;
	    int profit;
	 
	    // Constructor
	    Job(int start, int finish, int profit)
	    {
	        this.start = start;
	        this.finish = finish;
	        this.profit = profit;
	    }
	}
	 
	// Used to sort job according to finish times
	private static class JobComparator implements Comparator<Job>
	{
	    public int compare(Job a, Job b)
	    {
	        return a.finish < b.finish ? -1 : a.finish == b.finish ? 0 : 1;
	    }
	}

	public static int getTime(String g)
	{
		if(g.equals("12AM"))return 0;
		else if(g.equals("1AM"))return 1;
		else if(g.equals("2AM"))return 2;
		else if(g.equals("3AM"))return 3;
		else if(g.equals("4AM"))return 4;
		else if(g.equals("5AM"))return 5;
		else if(g.equals("6AM"))return 6;
		else if(g.equals("7AM"))return 7;
		else if(g.equals("8AM"))return 8;
		else if(g.equals("9AM"))return 9;
		else if(g.equals("10AM"))return 10;
		else if(g.equals("11AM"))return 11;
		else if(g.equals("12PM"))return 12;
		else if(g.equals("1PM"))return 13;
		else if(g.equals("2PM"))return 14;
		else if(g.equals("3PM"))return 15;
		else if(g.equals("4PM"))return 16;
		else if(g.equals("5PM"))return 17;
		else if(g.equals("6PM"))return 18;
		else if(g.equals("7PM"))return 19;
		else if(g.equals("8PM"))return 20;
		else if(g.equals("9PM"))return 21;
		else if(g.equals("10PM"))return 22;
		else if(g.equals("11PM"))return 23;
		else return -1;

	}
	
    /* A Binary Search based function to find the latest job
      (before current job) that doesn't conflict with current
      job.  "index" is index of the current job.  This function
      returns -1 if all jobs before index conflict with it.
      The array jobs[] is sorted in increasing order of finish
      time. */
    static public int binarySearch(Job jobs[], int index)
    {
        // Initialize 'lo' and 'hi' for Binary Search
        int lo = 0, hi = index - 1;
 
        // Perform binary Search iteratively
        while (lo <= hi)
        {
            int mid = (lo + hi) / 2;
            if (jobs[mid].finish <= jobs[index].start)
            {
                if (jobs[mid + 1].finish <= jobs[index].start)
                    lo = mid + 1;
                else
                    return mid;
            }
            else
                hi = mid - 1;
        }
 
        return -1;
    }
 
    // The main function that returns the maximum possible
    // profit from given array of jobs
    static public int schedule(Job jobs[])
    {
        // Sort jobs according to finish time
        Arrays.sort(jobs, new JobComparator());
 
        // Create an array to store solutions of subproblems.
        // table[i] stores the profit for jobs till jobs[i]
        // (including jobs[i])
        int n = jobs.length;
        int table[] = new int[n];
        table[0] = jobs[0].profit;
 
        // Fill entries in M[] using recursive property
        for (int i=1; i<n; i++)
        {
            // Find profit including the current job
            long inclProf = jobs[i].profit;
            int l = binarySearch(jobs, i);
            if (l != -1)
                inclProf += table[l];
 
            // Store maximum of including and excluding
            table[i] = (int) Math.max(inclProf, table[i-1]);
        }
 
        return table[n-1];
    }
 
	public static int profitValue(String[] input1)
	    {
		int n=input1.length;//number of jobs
		int st=0,et=0;
		Job jobs[]=new Job[n];
		for(int i=0;i<n;i++)
		{
			String x=input1[i];
			String h="";
			int u=0;
			while(x.charAt(u)!='#')
				{h+=x.charAt(u);
				u++;
				}
			st=getTime(h);
			u++;
			h=input1[i].substring(u);
			et=getTime(h);
			jobs[i]=new Job(st,et,500);
		}
		
    int result=schedule(jobs);
    return result;
	    }

