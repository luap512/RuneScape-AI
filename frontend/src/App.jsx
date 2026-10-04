import { useState } from 'react'
import './App.css'
import askService from './services/askService'

function App() {

  const [question, setQuestion] = useState('')
  const [answer, setAnswer] = useState('')
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)

  const handleAskQuestion = async () => {

    
    setLoading(true)
    setError(null)
    setAnswer('')

    try {

      const response = await askService.getAnswer(question)
      setAnswer(response.data)
      
    } 
    catch (error) {

      console.error('Error asking question:', error)
      setError(error)
    } 
    finally {

      setLoading(false)
    }
  }

  return (
    <>
      <section id="center">
        <div>
          <h1>Wise Old Man AI</h1>
          <div>
            <code>Ask a question:</code>
            <p>
              <input value = {question} onChange={(e) => setQuestion(e.target.value)}/>
            </p>
          </div>
        </div>
        <button
          type="button"
          className="counter"
          onClick={() => handleAskQuestion(question)}
          disabled={question.trim() === '' || loading}
        >
          {loading ? 'Loading...' : 'Ask'}
        </button>
        <section>
          <h2>Answer:</h2>
          <p>{answer}</p>
          {error && <p className="error">Error: {error.message}</p>}
        </section>
      </section>
    </>
  )
}

export default App
