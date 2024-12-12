export const mockQuestions = [
  {
    id: 1,
    type: 'mcq',
    question: 'What is the output of console.log(typeof [])?',
    options: ['array', 'object', 'undefined', 'null'],
    correctAnswer: 'object',
  },
  {
    id: 2,
    type: 'msq',
    question: 'Which of the following are JavaScript data types?',
    options: ['string', 'boolean', 'undefined', 'float'],
    correctAnswers: ['string', 'boolean', 'undefined'],
  },
  {
    id: 3,
    type: 'coding',
    question: 'Write a function that returns the sum of two numbers',
    testCases: [
      { input: [1, 2], expected: 3 },
      { input: [0, 0], expected: 0 },
      { input: [-1, 1], expected: 0 },
    ],
    initialCode: 'function sum(a, b) {\n  // Write your code here\n}',
  },
]