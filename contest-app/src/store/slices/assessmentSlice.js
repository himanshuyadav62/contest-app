import { createSlice } from '@reduxjs/toolkit'
import { mockQuestions } from '../../data/mockData'

const initialState = {
  questions: mockQuestions,
  currentQuestionIndex: 0,
  answers: {},
  timeRemaining: 3600, // 1 hour in seconds
}

const assessmentSlice = createSlice({
  name: 'assessment',
  initialState,
  reducers: {
    setAnswer: (state, action) => {
      const { questionId, answer } = action.payload
      state.answers[questionId] = answer
    },
    nextQuestion: (state) => {
      if (state.currentQuestionIndex < state.questions.length - 1) {
        state.currentQuestionIndex += 1
      }
    },
    previousQuestion: (state) => {
      if (state.currentQuestionIndex > 0) {
        state.currentQuestionIndex -= 1
      }
    },
    updateTimeRemaining: (state) => {
      if (state.timeRemaining > 0) {
        state.timeRemaining -= 1
      }
    },
  },
})

export const { setAnswer, nextQuestion, previousQuestion, updateTimeRemaining } = assessmentSlice.actions
export default assessmentSlice.reducer