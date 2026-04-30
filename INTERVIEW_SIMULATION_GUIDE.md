# Interview Simulation Walkthrough Guide

Navigation: [Main README](README.md) | [Mock Interview Scorecard](MOCK_INTERVIEW_SCORECARD.md) | [Behavioral Answer Bank](BEHAVIORAL_ANSWER_BANK.md) | [Interview Tracks](INTERVIEW_TRACKS.md)

This guide walks you through **realistic interview formats** with exact scripts, timing, evaluation criteria, and recovery strategies. Use this to prepare for phone screens, coding rounds, system design sessions, and behavioral interviews.

---

## 📋 Interview Types Overview

| Type | Duration | Focus | Questions | Format |
|------|----------|-------|-----------|--------|
| **Phone Screen** | 30-45 min | Communication + coding basics | 1-2 medium problems | Audio only |
| **Coding Interview** | 45-60 min | Problem solving + communication | 1-2 problems | Video + IDE/collaborative |
| **System Design** | 45-60 min | Architecture + trade-offs | 1 open-ended design | Whiteboard/Excalidraw |
| **Behavioral** | 30-60 min | Motivation + stories + culture fit | 3-5 behavioral questions | Conversational |
| **Final Round** | 60-90 min | Mixed of above (sometimes) | Various | Panel of 2-3 people |

---

## Phase 1: PHONE SCREEN (30-45 minutes)

**Goal:** Show you can code, communicate clearly, and handle pressure. This is a screening round to advance to on-site.

### Timeline Breakdown

| Time | Activity | Duration | Notes |
|------|----------|----------|-------|
| 0:00 | Interviewer intro + rapport | 2 min | Listen, nod, smile (if video) |
| 2:00 | Your background (30-60 sec story) | 1 min | Brief, relevant to role |
| 3:00 | Problem explanation | 2 min | Interviewer reads problem |
| 5:00 | Your clarification questions | 2 min | (See template) |
| 7:00 | Your approach (no coding yet) | 2 min | Walk through logic |
| 9:00 | Live coding | 20-25 min | Type and explain |
| 30:00 | Testing + edge cases | 5 min | Run through examples |
| 35:00 | Optimization opportunity | 5 min | Better time/space? |
| 40:00 | Your questions | 3-5 min | Ask about role/team |
| 45:00 | Close | 1 min | Thank you, next steps |

### Script: First 2 Minutes (Rapport Building)

**Interviewer:** "Hi! I'm [name], I'm a Senior Engineer at [company]. Thanks for talking with us today. How are you?"

**You:**
```
"Great, thanks for having me! I'm excited to talk with you. 
I've been preparing for this and am looking forward to coding with you today."
```

**Interviewer:** "Perfect. Let me briefly tell you what we're looking for. We want to see your problem-solving approach, 
how you write clean code, and how you communicate your thinking. There are no trick questions. Feel free to think out loud."

**You:**
```
"Sounds good. I appreciate that. I'll make sure to walk you through my thinking 
and let me know if anything is unclear."
```

**Interviewer:** "Great. Let me give you a quick bit about my background. I joined [company] [time] ago and work on [team]. 
Before that, I did [work]. Any questions before we jump in?"

**You:** 
```
"No, that sounds great. I'm ready when you are."
```

### Script: After Problem Explanation (Clarification Questions)

**Interviewer reads:** "Design an algorithm to find the two numbers in an array that sum to a target value."

**You:**
```
"Thanks for explaining that. Let me clarify a few things:

1. Can I assume the array has at least 2 elements?
2. Are all numbers positive integers, or could there be negatives/zeros?
3. Is there guaranteed to be a solution, or do I return null if none exists?
4. Can I modify the array, or does it need to stay unchanged?
5. If there are multiple pairs that sum to target, do I return just one or all?
6. What's the size of the array we're working with? (affects approach)

Also, would it help if I state my approach before I start coding?"
```

**Interviewer:** "Great questions. There are positive integers only, there's guaranteed to be a solution, 
you can modify it, return one pair, and the array is up to 10,000 elements."

### Script: Problem Approach (Before Coding)

**You:**
```
"Okay, so given the size of 10k elements, an O(n²) brute force would work but might be slow.
Here's my approach:

Approach 1 (Optimal):
- Use a hash map to store numbers I've seen
- Iterate through the array once
- For each number, check if (target - number) exists in the map
- If yes, return both numbers
- Time: O(n), Space: O(n)

I think this is the best approach here. Should I code that up?"
```

**Interviewer:** "Yes, go ahead."

### Script: During Coding (Thinking Out Loud)

**You:** (as you type)
```
"Let me start by creating a HashMap to track numbers I've seen...
Now I'll iterate through the array...
For each number, I'll first check if the complement (target - current) exists in my map...
If it does, I've found my pair, so I return it...
If not, I add the current number to my map and continue...
Let me write the code:

[Type code while narrating]

public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> seen = new HashMap<>();
    
    for (int num : nums) {
        int complement = target - num;
        
        if (seen.containsKey(complement)) {
            return new int[]{complement, num};
        }
        
        seen.put(num, 1);
    }
    
    return new int[]{};
}

Okay, I think that's the solution. Let me trace through an example..."
```

### Script: Testing Your Solution

**You:**
```
"Let me walk through an example:
Array: [2, 7, 11, 15], Target: 9

- i=0, num=2: complement=7, seen={}, add 2 → seen={2}
- i=1, num=7: complement=2, seen has 2! Return [2, 7] ✓

Let me check an edge case:
Array: [3, 3], Target: 6

- i=0, num=3: complement=3, seen={}, add 3 → seen={3}
- i=1, num=3: complement=3, seen has 3? (but it's same number)
  
Actually wait, let me verify this works... seen has key 3, so yes, we return [3, 3].
That should be fine since the problem allows using same number twice, right?"

[Ask for clarification if uncertain]
```

### Script: Optimization Opportunity

**Interviewer:** "Good solution. Can we do better on space complexity?"

**You:**
```
"With this problem, O(n) space is likely optimal because we need to store 
previously seen numbers to achieve O(n) time. 

We could theoretically do O(n log n) time with O(1) space if we:
1. Sort the array first: O(n log n)
2. Use two pointers from start and end
3. If sum is too small, move left pointer right
4. If sum is too large, move right pointer left
5. Return when we find the match

But that's actually worse overall. The hash map approach is what I'd recommend.
The memory trade-off is worth it for the linear time complexity."

[Only suggest this if you genuinely think of it. Don't force optimization if you can't explain it.]
```

### Script: Closing Questions (Last 3 Minutes)

**Interviewer:** "Do you have any questions for me?"

**You:**
```
"Yes, a few:

1. What does the day-to-day look like for someone on your team?
2. What's one thing you enjoy about working at [company]?
3. What's the product/feature you're most excited about right now?
4. What's the next step in this process if things go well?"
```

[Listen carefully. These answers tell you about culture/team.]

**Interviewer:** "Great questions. [Answers]. We'll follow up with you by [date] on next steps."

**You:**
```
"Perfect, thank you so much for your time. I really enjoyed talking with you, 
and I'm excited about the opportunity. See you soon!"
```

---

## Phase 2: CODING INTERVIEW (45-60 minutes)

**Goal:** One harder problem, deeper solution, real-world coding quality. Usually after you pass phone screen.

### Timeline Breakdown

| Time | Activity | Duration |
|------|----------|----------|
| 0:00 | Rapport + brief intro | 2 min |
| 2:00 | Problem explanation | 2-3 min |
| 5:00 | Clarifying questions | 3 min |
| 8:00 | Approach & discussion | 5 min |
| 13:00 | Live coding | 25-30 min |
| 38:00 | Testing & edge cases | 5 min |
| 43:00 | Optimization/discussion | 5 min |
| 48:00 | Questions for interviewer | 5 min |
| 53:00 | Close | 2 min |

### Key Differences from Phone Screen

| Aspect | Phone Screen | Coding Interview |
|--------|-------------|-------------------|
| **Problem Difficulty** | Medium | Medium-Hard |
| **Code Quality** | Functional | Production-ready |
| **Edge Cases** | 2-3 | 5-8 important ones |
| **Time Complexity** | Optimal is nice | Must have optimal |
| **Code Style** | Working | Clean, documented |
| **Follow-up Questions** | Rare | Expected |
| **Testing Depth** | Basic | Thorough |

### Problem Script: LRU Cache (Medium-Hard)

**Problem:** "Design an LRU (Least Recently Used) Cache with get and put operations that run in O(1) time."

**Your Clarification Questions:**
```
"Great. Let me clarify:

1. What's the max capacity of the cache?
2. On get/put, do we update the 'recently used' status?
3. If cache is full and we add a new item, remove the least recently used?
4. Should the API be thread-safe or single-threaded?
5. What are the value types - integers, objects, anything?

Also, before I design, should I mention my data structure approach?"
```

**Your Design (Before Coding):**
```
"I need O(1) get and O(1) put, which is tricky. Here's my approach:

Data Structures:
1. HashMap: key → Node (for O(1) access)
2. Doubly Linked List: most-recently-used → least-recently-used

Logic:
- get(key): Look up in map, if found, move node to head (most recent), return value
- put(key, value): 
  - If key exists, update value and move to head
  - If key is new:
    - If cache is full, remove tail (least recent)
    - Add new node at head
    - Add to map
  - Time: O(1), Space: O(capacity)

[Draw on whiteboard if available]

Should I code this up?"
```

**Your Code (With Narration):**
```java
class LRUCache {
    private class Node {
        int key, value;
        Node prev, next;
        
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private int capacity;
    private Map<Integer, Node> cache = new HashMap<>();
    private Node head, tail;  // dummy nodes
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (!cache.containsKey(key)) return -1;
        
        Node node = cache.get(key);
        moveToHead(node);  // Mark as recently used
        return node.value;
    }
    
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            if (cache.size() == capacity) {
                removeTail();
            }
            
            Node newNode = new Node(key, value);
            addToHead(newNode);
            cache.put(key, newNode);
        }
    }
    
    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }
    
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
    
    private void removeTail() {
        Node node = tail.prev;
        removeNode(node);
        cache.remove(node.key);
    }
}

[Explain as you code]

This uses a HashMap for O(1) lookup and a doubly-linked list to maintain LRU order.
The head is most recent, tail is least recent."
```

**Your Testing:**
```
"Let me trace through an example with capacity=2:

put(1, 1):     List: 1
               Map: {1→Node}

put(2, 2):     List: 2 ↔ 1
               Map: {1→Node, 2→Node}

get(1):        List: 1 ↔ 2    (move 1 to front, it's recent)
               Map: {1→Node, 2→Node}
               Return: 1 ✓

put(3, 3):     List: 3 ↔ 1
               Remove 2 (least recent)
               Map: {1→Node, 3→Node}

get(2):        Return: -1 (2 was removed) ✓

Edge cases:
- Capacity 1: Works (always remove and add immediately)
- Reusing keys: Move to head ✓
- Thread safety: Not implemented but mentioned in constraints ✓"
```

### Recovery Script: When You Get Stuck

**Situation:** You're 20 minutes in and realize your approach isn't optimal or doesn't work.

**What NOT to Do:**
- ❌ Panic or say "I don't know"
- ❌ Start over completely
- ❌ Blame the problem
- ❌ Go silent and think for 5+ minutes

**What TO Do:**

```
[Pause, take a breath]

"I realize my current approach might have an issue with [X]. 
Let me think about this differently...

[Explain what went wrong]

Actually, I think I should try [new approach]. Let me walk you through that:

[Describe new approach clearly]

Is that on the right track?"
```

**Example:**
```
"I was iterating twice through the array, but I realize I can optimize 
with a two-pointer approach instead. Let me refactor:

[Explain 2-pointer logic]

This would be O(n log n) due to sorting, but O(1) space. 
Is that a better direction than my current O(n) space approach?"
```

---

## Phase 3: SYSTEM DESIGN INTERVIEW (45-60 minutes)

**Goal:** Show architectural thinking, trade-offs, and ability to handle ambiguity at scale.

### Timeline Breakdown

| Time | Activity | Duration |
|------|----------|----------|
| 0:00 | Rapport + context | 2 min |
| 2:00 | Problem statement | 2 min |
| 4:00 | Requirements gathering | 8 min |
| 12:00 | Capacity estimation | 5 min |
| 17:00 | High-level design | 10 min |
| 27:00 | Deep dives (2-3 areas) | 15 min |
| 42:00 | Failover & scaling | 5 min |
| 47:00 | Your questions | 5 min |
| 52:00 | Close | 2 min |

### System Design Problem: Design a URL Shortener (like bit.ly)

**Problem:** "Design a URL shortening service. Users can paste a long URL and get a short code to share."

#### Step 1: Requirements Gathering (First 8 minutes)

**You:**
```
"Before I design, let me clarify requirements:

FUNCTIONAL REQUIREMENTS:
1. User pastes long URL → system returns short code
2. User visits short URL → redirected to original long URL
3. Should users be able to customize their short code?
4. Can links expire or be deleted?

NON-FUNCTIONAL REQUIREMENTS:
5. How many users might use this service? (scale)
6. What's the daily/yearly URL shortening volume?
7. What's the expected redirect volume (reads/writes ratio)?
8. What regions does this need to serve?
9. What's the URL length limit?
10. Can the short code repeat after expiry?"
```

**Interviewer:** "Good questions. 100M users might use it, 1M new links per day, 100x reads vs writes, 
global reach, standard URL lengths, and links should never expire."

#### Step 2: Capacity Estimation (5 minutes)

**You:**
```
"Great. Let me do some quick math:

Write capacity:
- 1M new URLs per day
- = 1M / 86400 = ~12 URLs per second
- Peak might be 100x normal = 1000 URLs/sec

Read capacity:
- 100x write ratio = 100,000 reads/sec
- Peak could be 500,000 reads/sec

Storage (5-year horizon):
- 1M URLs/day × 365 days × 5 years = 1.8B URLs
- Per URL: ~500 bytes (original URL + metadata)
- = 1.8B × 500 = 900 GB
- With redundancy (3x) = 2.7 TB (totally manageable)

Short code length:
- If I use base-62 (a-z, A-Z, 0-9)
- Length 6 = 62^6 = 56 trillion possible codes
- For 1.8B, length 6 is safe. I'll use that.

So I need to handle 100K read QPS at scale, writes are lighter."
```

#### Step 3: High-Level Design (10 minutes)

**You:** (Draw/describe)
```
"Here's my high-level architecture:

┌─────────────┐
│   Client    │
└──────┬──────┘
       │ HTTP
       ▼
┌──────────────────────┐
│  Load Balancer       │ (route to nearest region)
└──────┬───────────────┘
       │
┌──────┴───────────────────────────┐
│                                  │
▼                                  ▼
┌──────────────┐          ┌──────────────┐
│  API Server  │          │  API Server  │
│  (Region A)  │          │  (Region B)  │
└──────┬───────┘          └──────┬───────┘
       │                          │
       ├──────────────┬───────────┤
       ▼              ▼           ▼
┌────────────┐  ┌────────────┐
│Redis Cache │  │  Database  │  (replicated)
│(hot links) │  │  (primary) │
└────────────┘  └────────────┘
                │
                ▼
          ┌──────────────┐
          │  Database    │  (read replica)
          │  (replica)   │
          └──────────────┘

Components:
1. Load Balancer: Route by geography, health check
2. API Servers: Stateless, handle create/redirect
3. Cache (Redis): Hot URLs, 80/20 rule applies
4. Primary DB: Write here, with replication for HA
5. Read Replicas: Handle read queries

Trade-offs:
- Cache adds latency but reduces DB load (good for 100K QPS)
- Replication adds complexity but guarantees HA
- Simple write logic (no distributed transactions needed)"
```

#### Step 4: Deep Dives (Choose 2-3) (15 minutes)

**Deep Dive 1: URL Encoding Algorithm**

```
"For the short code generation, I'd use a counter-based approach:

Option A: Random generation
- Generate random 6-character code
- Check if it exists in DB
- If yes, regenerate; if no, use it
- Downside: collision checks slow us down

Option B: Counter-based (BETTER)
- Use atomic counter (e.g., Zookeeper, Redis INCR)
- Counter starts at 0, increments for each new URL
- Convert counter value to base-62: easy, fast, collision-free
- Algorithm:
  long counter = atomicCounter.increment();
  String shortCode = toBase62(counter);
  return shortCode;
- Upside: O(1), no collisions ever
- Downside: Predictable codes (not a security requirement)

I'd go with Option B for reliability and performance.
Scaling this: multiple servers can share the same counter via Redis."
```

**Deep Dive 2: Handling 100K Read QPS**

```
"For 100K reads/sec, I need multiple strategies:

1. CACHING LAYER:
   - Cache hot/popular URLs in Redis
   - Eviction: LRU (least recently used)
   - TTL: 24 hours for popular links
   - Estimate: 20% of traffic should hit cache
   - That's 20K/sec from cache vs DB

2. DATABASE OPTIMIZATION:
   - Table: CREATE TABLE urls (
       id INT PRIMARY KEY,
       short_code VARCHAR(6) UNIQUE,
       original_url TEXT,
       created_at TIMESTAMP,
       INDEX (short_code)  // Critical for lookups
     )
   - Sharding: Later, if needed (for now, single DB is fine)

3. CDN + EDGE:
   - For top 1% most popular URLs
   - Serve redirects from edge servers (geo-distributed)
   - Reduces latency and DB pressure
   - Cache headers: max-age=1 hour

4. ASYNC LOGGING:
   - Analytics (clicks, referrers) go to message queue
   - Async processors update analytics DB
   - Doesn't block the redirect response (critical!)"
```

**Deep Dive 3: Failover & Replication**

```
"For reliability at this scale:

WRITE PATH:
1. Client sends create() to API via load balancer
2. API server writes to PRIMARY database
3. Primary replicates to secondary (MySQL replication lag ~100ms)
4. Response sent immediately (don't wait for replica)
5. If primary fails, promote secondary (downtime: ~30 seconds)

READ PATH:
1. Client sends get(short_code) via load balancer
2. API queries Cache first (Redis)
3. Cache miss → Query read replica (not blocking primary)
4. If both fail → Error response (graceful degradation)

Monitoring:
- Alert if replication lag > 1 second
- Circuit breaker if primary is down
- Health checks every 5 seconds"
```

#### Step 5: Scaling & Trade-offs (5 minutes)

**You:**
```
"If we hit 10M URLs/day (10x current), we'd need:

1. SHARDING:
   - Shard by short_code range
   - Shard 1: aaa000-kkk999
   - Shard 2: lll000-uuu999
   - Etc.
   - Each shard has primary + replica
   - Requires sharding library (Vitess, etc.)

2. GLOBAL REPLICATION:
   - Primary in US, replicas in EU and APAC
   - Multi-region consistency is tricky
   - Consider read-your-write consistency (not global consistency)

3. CONSIDERATIONS NOT IN MVP:
   - Duplicate detection (is this URL already shortened?)
   - Custom short codes (vanity URLs)
   - Link analytics (clicks, geography)
   - Link expiration with cleanup
   
Any of those you want to explore in detail?"
```

---

## Phase 4: BEHAVIORAL INTERVIEW (30-60 minutes)

**Goal:** Understand your soft skills, culture fit, and past work experience.

### Standard Behavioral Questions (STAR Format)

#### Question 1: "Tell me about a challenging problem you solved."

**Your Answer (Using STAR):**

```
SITUATION:
"At [company], I was building a payment processing system that needed 
to handle high-volume transactions during peak hours."

TASK:
"The challenge was that our old caching strategy caused inconsistencies 
when payment status updates weren't synced properly across servers."

ACTION:
"Here's what I did:

1. Diagnosed the problem:
   - Analyzed logs to understand the race condition
   - Found that we had no ordering guarantee between cache and DB writes
   
2. Proposed a solution:
   - Implemented a write-aside cache pattern with versioning
   - Used a message queue for ordered updates
   
3. Implemented:
   - Wrote the code with proper transaction handling
   - Set up monitoring for inconsistencies
   - Created runbooks for debugging
   
4. Tested thoroughly:
   - Unit tests for edge cases
   - Load tests with synthetic traffic
   - Shadow testing in production (1% of traffic)"

RESULT:
"We reduced payment sync errors from 0.1% to less than 0.01%, 
improved latency by 30%, and rolled out with zero incidents. 
I also documented the pattern so other teams could use it."

REFLECTION:
"What I learned was the importance of testing at scale and 
having clear monitoring from day one. I would've started with 
load testing earlier next time."
```

#### Question 2: "Tell me about a time you disagreed with your manager/colleague."

**Your Answer:**

```
SITUATION:
"At [company], the team wanted to rewrite our auth system from 
custom code to an open-source library."

TASK:
"I was assigned to evaluate the migration, but I disagreed with 
the timeline (2 weeks seemed too aggressive)."

ACTION:
"Instead of just saying 'no':

1. I did my research:
   - Wrote a detailed assessment of migration risks
   - Created a phased rollout plan (4 weeks instead of 2)
   - Documented all edge cases and compatibility issues
   
2. Presented data:
   - Showed similar companies' migration timelines
   - Explained the cost of rushing (bugs, incidents, on-call)
   - Proposed a proof of concept first (1 week)
   
3. Collaborated:
   - Listened to their business reasons (wanted feature X)
   - Found a compromise: prioritize the POC, then decide"

RESULT:
"We did the POC in week 1, discovered 3 critical issues, 
and my 4-week plan actually prevented about 10 days of 
bug fixes down the road. The team appreciated the thoroughness."

REFLECTION:
"I learned to present disagreements with data, not opinions, 
and to understand the 'why' before pushing back."
```

#### Question 3: "Describe a situation where you had to learn something new quickly."

**Your Answer:**

```
SITUATION:
"At [company], I was moved to the payments team but had no 
experience with payment systems or PCI compliance."

TASK:
"I had 2 weeks before I needed to review payment transaction 
code and understand the compliance requirements."

ACTION:
"Here's my learning approach:

1. Self-study (Days 1-3):
   - Read PCI DSS compliance docs (6 hours)
   - Took online course on payment processing (4 hours)
   - Reviewed our codebase (6 hours)
   
2. Asked for help (Days 4-7):
   - Had 1-on-1 with team lead (did payment before)
   - Watched code reviews, asked questions
   - Did a mini project: add field validation for cards
   
3. Taught others (Days 8-14):
   - Wrote 3 documentation pages for team
   - Did a tech talk: 'Payments 101 for new team members'
   - Helped onboard next person joining team"

RESULT:
"By week 2, I was able to review code effectively. Within 
a month, I found 2 PCI compliance issues that other reviewers 
missed because I'd just learned everything."

REFLECTION:
"I realized teaching others helps consolidate my own learning. 
Now I always document as I learn something new."
```

### Follow-up Questions (Must Prepare For)

| Follow-up | How to Answer |
|-----------|---------------|
| "What would you do differently?" | Show growth mindset + learning |
| "How did you measure success?" | Use metrics (faster, fewer bugs, better UX) |
| "Who did you collaborate with?" | Show teamwork + humility |
| "What was the hardest part?" | Be honest about challenges |
| "Did you fail? How?" | Own the failure + recovery |

### Company Culture Questions Interviewer Will Ask

#### "Why do you want to work at [Company]?"

**Bad Answer:** "I want to grow my career."  
**Good Answer:**
```
"I've been impressed by [company]'s work on [specific problem].
What drew me specifically was [project/technology/team].

I saw the case study on [specific thing], and I thought
[specific technical insight]. That aligns with my interests in [area].

Also, I've heard great things from [specific person] about 
the engineering culture here—particularly [specific aspect]."
```

#### "What's something you're not good at?"

**Bad Answer:** "I work too hard." (Obvious BS)  
**Good Answer:**
```
"Early in my career, I struggled with communication-heavy tasks
like giving presentations. I'd over-prepare and get anxious.

So I took a public speaking course, volunteered to give 
tech talks, and practiced in low-stakes environments first.

Now I actually enjoy presenting, and I can convey complex 
ideas clearly. It's still an area I'm growing, but much better."

[Shows: self-awareness + action + growth]
```

---

## 🎯 Holistic Interview Preparation Checklist

### 1-2 Weeks Before Interviews

- [ ] Re-solve [Blind 75 problems](BLIND_75_PROBLEM_MAPPING.md) (easy → medium → hard)
- [ ] Mock interview with friend (use this guide)
- [ ] Record yourself answering behavioral questions
- [ ] Review [BEHAVIORAL_ANSWER_BANK.md](BEHAVIORAL_ANSWER_BANK.md)
- [ ] Prepare 6 STAR stories (technical challenge, failure, disagreement, learning, team, impact)
- [ ] Research companies (products, recent news, engineering challenges)

### 3 Days Before Interview

- [ ] Review [QUICK_REFERENCE_GUIDES.md](QUICK_REFERENCE_GUIDES.md)
- [ ] Do easy problems (5-10) without timer to build confidence
- [ ] Practice whiteboarding (on actual whiteboard if possible)
- [ ] Check internet connection, camera, microphone
- [ ] Prepare questions for interviewer (show genuine interest)

### Day Of Interview

- [ ] Get good sleep (8+ hours)
- [ ] Light breakfast/lunch (not heavy)
- [ ] Arrive 10 minutes early (for on-site) or log in 5 min early (video)
- [ ] Dress professionally (or match company culture)
- [ ] Have water nearby
- [ ] Close unnecessary browser tabs
- [ ] Have notes with key algorithms visible (if permitted)

### Interview Day Timeline

| Time | Action |
|------|--------|
| 15 min before | Take 3 deep breaths, review interview type |
| 5 min before | Smile, introduce yourself warmly |
| During | Speak clearly, explain thinking, ask clarifications |
| If stuck | "Let me think about this..." (pause is OK) |
| After | Thank interviewer, confirm next steps |

---

## Recovery Script: When Things Go Wrong

### Scenario 1: You Blank on a Problem

```
"I feel like I should know this, but let me think... 

Actually, let me ask you a clarifying question: 
[ask something that helps you think]

Or put another way, the approach I see is [different approach].
Does that work, or is there something I'm missing?"
```

### Scenario 2: Code Doesn't Compile

```
"Let me fix this syntax error...

[Fix it]

There we go. Sorry about that. Let me make sure the logic is correct now..."
```

### Scenario 3: You Make a Logical Mistake

```
"Wait, I think I found a bug in my logic here. 

Let me trace through this example... 
Actually that's wrong because of [X].

Let me correct it..."

[Fix it, don't panic]
```

### Scenario 4: Network Cuts Out (Video Interview)

```
[Typing in chat]  "Sorry, my connection dropped. Reconnecting..."
[Reconnect]       "I'm back. I was saying [where you left off]..."
```

### Scenario 5: Interviewer is Tough/Critical

```
[Stay calm, don't get defensive]

"That's a fair point. How would you approach that differently?"

[Listen, learn, adapt]

"That makes sense. Let me implement that..."
```

---

## Post-Interview: What To Do Next

### Immediately After (Within 1 hour)

1. **Write Down:**
   - What problems were asked
   - How comfortable you felt (0-10 scale)
   - What went well
   - What didn't go well
   - Edge cases you missed

2. **Use [MOCK_INTERVIEW_SCORECARD.md](MOCK_INTERVIEW_SCORECARD.md)** to evaluate yourself

3. **Send Follow-up Email:**
```
Subject: Thank You - [Your Name]

Hi [Interviewer Name],

Thank you so much for taking the time to interview me today. 
I really enjoyed discussing [specific problem/topic].

I'm particularly excited about [specific thing about company/role]. 
I believe my experience with [your background] aligns well with 
what you're looking for.

Please let me know if you need any additional information.

Best regards,
[Your Name]
```

### Later That Week

- Study solution to problem you got wrong
- Re-solve it 2-3 times
- Search for variations of the problem
- Add to [Daily Problem Log](DAILY_PROBLEM_LOG.md) as a failed attempt

### Before Next Interview

- Review feedback from practice ← most important
- Focus on weak areas
- Do 3-5 similar problems before repeating that round

---

## 📊 Interview Success Metrics

| Metric | Target | Tracking |
|--------|--------|----------|
| **Phone Screen** | 70%+ advance rate | Count passes in [Scorecard](MOCK_INTERVIEW_SCORECARD.md) |
| **Coding Round** | 80%+ problem completion | Track in [Problem Log](DAILY_PROBLEM_LOG.md) |
| **System Design** | Interviewer says "good architecture" | Ask for feedback |
| **Behavioral** | 5/5 on STAR stories | Record yourself, self-score |
| **Overall** | 50%+ offer rate (from phone screens) | Take home assignments count |

---

## 💡 Final Pro Tips

1. **Communicate > Perfect Code** - Interviewers prefer "I think this way..." over silent brilliance
2. **Ask Clarifying Questions** - Shows maturity and prevents wrong solutions
3. **Edge Cases Matter** - Spend 5-10 min on boundary conditions (off-by-one, nulls, etc.)
4. **Test Your Solution** - Trace through an example before saying "done"
5. **Explain Trade-offs** - "This is O(n) time but O(n) space. An alternative is..."
6. **Be Honest** - "I don't know but here's how I'd figure it out" is better than BS
7. **Stay Calm Under Pressure** - Deep breaths, think out loud, ask for hints if needed
8. **Follow Up** - Email within 24 hours, ask about timing for next steps

---

## 🔗 Related Documents

- [MOCK_INTERVIEW_SCORECARD.md](MOCK_INTERVIEW_SCORECARD.md) - Self-evaluation template
- [BEHAVIORAL_ANSWER_BANK.md](BEHAVIORAL_ANSWER_BANK.md) - STAR story templates
- [QUICK_REFERENCE_GUIDES.md](QUICK_REFERENCE_GUIDES.md) - Quick lookup during practice
- [BLIND_75_PROBLEM_MAPPING.md](BLIND_75_PROBLEM_MAPPING.md) - Curated problems by company
- [INTERVIEW_TRACKS.md](INTERVIEW_TRACKS.md) - Company-specific prep paths
