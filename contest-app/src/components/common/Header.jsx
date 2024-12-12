import Timer from './Timer'

function Header() {
  return (
    <header className="bg-white shadow">
      <div className="container mx-auto px-4 py-4 flex justify-between items-center">
        <h1 className="text-2xl font-bold text-gray-800">Online Assessment</h1>
        <Timer />
      </div>
    </header>
  )
}

export default Header