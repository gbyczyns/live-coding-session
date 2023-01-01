# Live coding session

This is an hour session, 45 minutes of which will be spent iterating on a problem in your IDE. We will be looking at how you approach solving problems through code, and what the resulting code looks like - we like clean, simple, maintainable and tested code.

What’s important:
- **Solving the problem** (focus)
- **Code quality** (write maintainable code)
- **Speed** (while ensuring correctness).

Expect a series of tasks with well defined goals, and don’t expect to solve all of them - we’re looking at the code that remains. What rules,
 patterns and techniques do you apply, are these choices conscious.

Keep the solution simple. Remember: you can only get to the next task upon passing the first one.

Revise Java fundamentals, data structures, algorithms, concurrency and testing. For practice, we would recommend a coding kata

Please prepare: Your IDE with an empty Java project, with any testing libraries you usually use.

## Tasks
1. Create a load balancer class that allows to register a URL address. The maximum number of accepted addresses is 10. Each address should be unique.
2. Implement get() method with algorithm that randomly returns one of registered URLs.
3. Develop an algorithm that, when invoking multiple times the Load Balancer get() method, should return a URL choosing between the registered one sequentially (round-robin).