import React from 'react'
import { Routes, Route } from 'react-router-dom'
import AssessmentPage from './pages/AssessmentPage'
import Header from './components/common/Header'

function App() {
  return (
    <div className="min-h-screen bg-gray-50">
      <Header />
      <main className="container mx-auto px-4 py-8">
        <Routes>
          <Route path="/" element={<AssessmentPage />} />
        </Routes>
      </main>
    </div>
  )
}

export default App