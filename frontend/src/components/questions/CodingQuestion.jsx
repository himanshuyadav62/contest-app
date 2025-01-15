import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Editor } from "@monaco-editor/react";
import { setAnswer } from "../../store/slices/assessmentSlice";
import {
  Select,
  SelectTrigger,
  SelectValue,
  SelectContent,
  SelectItem,
} from "../ui/select";

// Language and theme options
const LANGUAGES = [
  { value: "javascript", label: "JavaScript" },
  { value: "python", label: "Python" },
  { value: "java", label: "Java" },
  { value: "cpp", label: "C++" },
  { value: "typescript", label: "TypeScript" },
];

const THEMES = [
  { value: "vs-dark", label: "Dark" },
  { value: "light", label: "Light" },
  { value: "vs-code", label: "VS Code" },
  { value: "monokai", label: "Monokai" },
];

function CodingQuestion({ question }) {
  const dispatch = useDispatch();
  const answer = useSelector(
    (state) => state.assessment.answers[question.id] || question.initialCode
  );

  // State for language and theme selection
  const [language, setLanguage] = useState("javascript");
  const [theme, setTheme] = useState("vs-dark");

  const handleEditorChange = (value) => {
    dispatch(setAnswer({ questionId: question.id, answer: value }));
  };

  return (
    <div className="flex flex-col md:flex-row w-full p-4 bg-white shadow-lg rounded-xl space-y-4 md:space-y-0 md:space-x-4">
      {/* Question and Test Cases Section */}
      <div className="w-full md:w-1/2 space-y-4">
        <div className="bg-gray-100 p-4 rounded-lg">
          <h3 className="text-xl font-bold text-gray-800 mb-3">
            {question.question}
          </h3>
        </div>

        {/* Test Cases */}
        <div className="bg-gray-50 p-4 rounded-lg">
          <h4 className="text-lg font-semibold text-gray-700 mb-3">
            Test Cases:
          </h4>
          <div className="space-y-2">
            {question.testCases.map((testCase, index) => (
              <div
                key={index}
                className="bg-white p-3 border border-gray-200 rounded-md shadow-sm"
              >
                <p className="text-sm">
                  <span className="font-medium">Input:</span>{" "}
                  {JSON.stringify(testCase.input)}
                </p>
                <p className="text-sm">
                  <span className="font-medium">Expected Output:</span>{" "}
                  {JSON.stringify(testCase.expected)}
                </p>
              </div>
            ))}
          </div>
        </div>
      </div>

      {/* Monaco Editor Section */}
      <div className="w-full md:w-1/2 h-[500px] border-2 border-gray-200 rounded-lg overflow-hidden flex flex-col">
        <div className="flex space-x-4 mb-4 p-4 bg-gray-100 rounded-t-lg">
          <div className="w-1/2">
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Language
            </label>
            <Select value={language} onValueChange={setLanguage}>
              <SelectTrigger>
                <SelectValue placeholder="Select Language" />
              </SelectTrigger>
              <SelectContent>
                {LANGUAGES.map((lang) => (
                  <SelectItem key={lang.value} value={lang.value}>
                    {lang.label}
                  </SelectItem>
                ))}
              </SelectContent>
            </Select>
          </div>

          <div className="w-1/2">
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Theme
            </label>
            <Select value={theme} onValueChange={setTheme}>
              <SelectTrigger>
                <SelectValue placeholder="Select Theme" />
              </SelectTrigger>
              <SelectContent>
                {THEMES.map((themeOption) => (
                  <SelectItem key={themeOption.value} value={themeOption.value}>
                    {themeOption.label}
                  </SelectItem>
                ))}
              </SelectContent>
            </Select>
          </div>
        </div>
        <div className="flex-1">
          <Editor
            height="100%"
            language={language}
            theme={theme}
            value={answer}
            onChange={handleEditorChange}
            options={{
              minimap: { enabled: false },
              fontSize: 14,
              lineNumbers: "on",
              automaticLayout: true,
              wordWrap: "on",
            }}
          />
        </div>
      </div>
    </div>
  );
}

export default CodingQuestion;
